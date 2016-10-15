package com.taras.logic;

import com.taras.config.GameDimensions;
import com.taras.config.IGameMatrixState;
import com.taras.config.IGameMenu;
import com.taras.ui.IUserInterface;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Taras on 15.10.2016.
 */
public class FileManager {

    private IGameMenu iGameMenu;
    private IUserInterface iUserInterface;
    private IGameMatrixState iGameMatrixState;
    private File savedGame;
    private String savedGamePath;
    private Date time = new Date();
    private DateFormat dateFormat = new SimpleDateFormat(" dd MM (HH mm ss)");

    public FileManager(IGameMenu iGameMenu,IUserInterface iUserInterface,IGameMatrixState iGameMatrixState) {
        this.iGameMenu = iGameMenu;
        this.iUserInterface = iUserInterface;
        this.iGameMatrixState = iGameMatrixState;
    }

    private void fileWriter(int gameMatrix[][], File file){
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
                                iGameMatrixState.setGameMatrixElement(p,k,Integer.parseInt(matrixElement));
                                if (iGameMatrixState.getMatrixElement(p,k)==0){
                                    iGameMatrixState.setStartZeroX(p);
                                    iGameMatrixState.setStartZeroY(k);
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
            iGameMatrixState.newGame();
        }

        iGameMenu.setNewGame(iGameMatrixState.getGameMatrix());
    }

    public String getSavedGamePath() {

        return savedGamePath;
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
                fileWriter(iGameMatrixState.getGameMatrix(),savedGame);
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
