package com.taras.logic;

import com.taras.config.GameDimensions;
import com.taras.config.IGameMenu;
import com.taras.ui.GameUi;
import com.taras.ui.IUserInterface;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Taras on 21.09.2016.
 */
public class FifteenEngine{


    private Random random = new Random();
    private int num = 1; //Counter used to fiil the Array
    private int startZeroX = GameDimensions.DISPLAY_X - 1; //Sarting position of zero button
    private int startZeroY = GameDimensions.DISPLAY_Y - 1;
    private int gameMatrix[][];
    private boolean won = false;
    private File savedGame;
    private String savedGamePath;
    private Date time = new Date();
    private DateFormat dateFormat = new SimpleDateFormat(" dd MM (HH mm ss)");


    /**
     * Call the {@link IUserIanterface#swapItems(int, int, int, int)} method of this object to
     * swap items inside the UI.
     */
    public IUserInterface iUserInterface;
    public IGameMenu iGameMenu;

    public FifteenEngine(IUserInterface iUserInterface,IGameMenu iGameMenu)  {
        this.iGameMenu = iGameMenu;
        this.iUserInterface = iUserInterface;
        gameMatrix = new int[GameDimensions.DISPLAY_X][GameDimensions.DISPLAY_Y];
    }

    public void processPressedKey(char keyPressedLabel) {

        // Some moves by WSAD
        switch (keyPressedLabel) {

            case 's':

                if (startZeroX>0){
                    swapMatrixElements(startZeroX,startZeroY,startZeroX-1,startZeroY);
                    startZeroX--;
                }
                break;
            case 'a':
                if (startZeroY<3) {
                    swapMatrixElements(startZeroX,startZeroY,startZeroX,startZeroY+1);
                    startZeroY++;
                }
                break;
            case 'w':
                if (startZeroX<3){
                    swapMatrixElements(startZeroX,startZeroY,startZeroX+1,startZeroY);
                    startZeroX++;
               }
                break;
            case 'd':
                if (startZeroY>0){
                    swapMatrixElements(startZeroX,startZeroY,startZeroX,startZeroY-1);
                    startZeroY--;
                }
                break;
        }
    }

    public void createStartState(int maxMoveNumber) {
        int num = 1; //Counter used to fiil the Array]
        int numberOfMoves = 0;
        startZeroX = GameDimensions.DISPLAY_X-1;
        startZeroY = GameDimensions.DISPLAY_Y-1;
        // create and fill the Array
        for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
            for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {
                gameMatrix[k][p] = num++;
            }
        }
        gameMatrix[startZeroX][startZeroY] = 0;

        // Random moves by swapping them (4 direction)
        do {
            int randomMoveDirection = random.nextInt(4);
            numberOfMoves++;

            switch (randomMoveDirection) {
                case 0:
                    if (startZeroX > 0) {
                        gameMatrix[startZeroX][startZeroY] = gameMatrix[startZeroX - 1][startZeroY];
                        gameMatrix[startZeroX-1][startZeroY] = 0;
                        startZeroX--;
                    }
                    break;

                case 1:
                    if (startZeroY > 0) {
                        gameMatrix[startZeroX][startZeroY] = gameMatrix[startZeroX][startZeroY - 1];
                        gameMatrix[startZeroX][startZeroY-1] = 0;
                        startZeroY--;
                    }
                    break;

                case 2:
                    if (startZeroX < 3) {
                        gameMatrix[startZeroX][startZeroY] = gameMatrix[startZeroX + 1][startZeroY];
                        gameMatrix[startZeroX+1][startZeroY] = 0;
                        startZeroX++;
                    }
                    break;

                case 3:
                    if (startZeroY < 3) {
                        gameMatrix[startZeroX][startZeroY] = gameMatrix[startZeroX][startZeroY+1];
                        gameMatrix[startZeroX][startZeroY+1] = 0;
                        startZeroY++;
                    }
                    break;
            }

        } while (numberOfMoves < maxMoveNumber);

    }
// method used for swap elemetns in matrix and check for win state
    private void swapMatrixElements(int startZeroX, int startZeroY, int finalZeroX, int finalZeroY){
        gameMatrix[startZeroX][startZeroY] = gameMatrix[finalZeroX][finalZeroY];
        gameMatrix[finalZeroX][finalZeroY] = 0;
        iUserInterface.swapItems(startZeroX,startZeroY,finalZeroX,finalZeroY);


    }

//getter of game matrix
    public Integer getMatrixElement(int x, int y) {

        return gameMatrix[x][y];
    }
    public String getSavedGamePath() {

        return savedGamePath;
    }


// method for checking winning state
    public boolean winConfirming(){
        num = 1;
        for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
            for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {
                if (getMatrixElement(k,p) == num){
                    num++;
                }
            }
        }
        return num == GameDimensions.DISPLAY_X*GameDimensions.DISPLAY_Y;
    }



    // method used to create new game (refill game matrix)
    public void newGame(){

        createStartState(1000);
        iGameMenu.setNewGame(gameMatrix);
    }

    //method for write game progress into the file
    public void fileWriter(int gameMatrix[][],File file){
        try {
            PrintWriter printWriter = new PrintWriter(file.getAbsoluteFile());
            try{
                for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
                    for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {
                        printWriter.write(""+gameMatrix[p][k]+" ");
                }
            }
        }finally {
                printWriter.close();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
// Method for load from file game state
    public void loadGame(int type){
        if (type == 1){
            savedGame = iGameMenu.getSavedGame();
        }
        if (type == 2){
            savedGame = new File(savedGamePath, "/autosave.fft");
        }
        StringBuilder stringBuilder = new StringBuilder();
        int count;
        int count1 = 0;
        char c;
        String matrixElement = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(savedGame.getAbsoluteFile()));
            try{
                String string;
                if((string = bufferedReader.readLine()) != null){
                    stringBuilder.append(string);
                }
                for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
                    for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {
                        for (count=count1;count<string.length();count++){
                            char stringElement = string.charAt(count);
                            if(stringElement != ' '){
                                matrixElement+=stringElement;
                            }
                            else{
                                gameMatrix[p][k] = Integer.parseInt(matrixElement);
                                if (gameMatrix[p][k] == 0){
                                    startZeroX = p;
                                    startZeroY = k;
                                }
                                matrixElement = "";
                                count1=count+1;
                                break;

                            }
                        }

                    }
                }

            }finally {
                bufferedReader.close();
            }
        }
//FIXME: NEED TO CHANGE INTO FILENOTFOUNDEXEPTION. THEN START NEW GAME!!!!!!!
        catch (IOException e) {
            System.out.println(e.toString());
            newGame();
            }

        iGameMenu.setNewGame(gameMatrix);
    }

//method used for save game state into the file
    public void saveGame(int type){
        // Classic save
        if (type == 1) {
            savedGame = new File(savedGamePath, "" + iGameMenu.setSaveGame() + dateFormat.format(time) + ".fft");
        }
        // Save using close button
        if (type == 2){
//            try{
            savedGame = new File(savedGamePath, "/autosave.fft");
//            }
//            catch (FileNotFoundException e){
//                System.out.println("rrr ");
//            }
            if (savedGame.exists()){
                savedGame.delete();
            }
        }
        try {
            boolean created = savedGame.createNewFile();
            if(created){
                fileWriter(gameMatrix,savedGame);
                System.out.println("Game saved");
                if (type == 1){
                    iUserInterface.savedGameMassage();
                }
            }
        } catch (IOException e) {
            System.out.println("Game doesn't saved. Please try again");
        }
    }
    // Method used for create or fill (if it allready created) config file;
    public void setConfigFile (){
        File configFile = new File("config.txt");
        try {
                if(configFile.exists()){
                    FileReader fileReader = new FileReader(configFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    savedGamePath = bufferedReader.readLine();
                    if(savedGamePath == null){
                        try{
                            savedGamePath = iGameMenu.setSavedGamePath();
                            PrintWriter printWriter = new PrintWriter(configFile.getAbsoluteFile());
                            printWriter.write(savedGamePath);
                            printWriter.close();
                        }finally {
                            bufferedReader.close();
                        }
                    }
                }                else{

                    Boolean created = configFile.createNewFile();
                    PrintWriter printWriter = new PrintWriter(configFile.getAbsoluteFile());
                    if(created){
                        try{
                        savedGamePath = iGameMenu.setSavedGamePath();
                        printWriter.write(savedGamePath);
                        }
                        finally {
                            printWriter.close();

                        }
                    }
                }

            }catch (IOException e) {
                e.printStackTrace();
            }
            configFile.setReadOnly();
    }

}




