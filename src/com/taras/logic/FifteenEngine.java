package com.taras.logic;

import com.taras.config.GameDimensions;
import com.taras.ui.GameUi;
import com.taras.ui.IUserInterface;

import java.util.Random;

/**
 * Created by Taras on 21.09.2016.
 */
public class FifteenEngine{

    private Random random = new Random();

    private int startZeroX = GameDimensions.DISPLAY_X - 1; //Sarting position of zero button
    private int startZeroY = GameDimensions.DISPLAY_Y - 1;
    private int finalZeroX = 0; //Position of target button
    private int finalZeroY = 0;
    private int num = 1; //Counter used to fiil the Array
    private int numberOfMoves = 0;
    private int gameMatrix[][];
    GameUi gameUi =new GameUi();



    /**
     * Call the {@link IUserIanterface#swapItems(int, int, int, int)} method of this object to
     * swap items inside the UI.
     */
    public IUserInterface iUserInterface;



    public void registerSwapItems(IUserInterface iUserInterface){
        this.iUserInterface = iUserInterface;
    }

    public FifteenEngine(IUserInterface iUserInterface)  {
        this.iUserInterface = iUserInterface;
        gameMatrix = new int[GameDimensions.DISPLAY_X][GameDimensions.DISPLAY_Y];
    }

    public void processPressedKey(char keyPressedLabel) {

        // Some moves by WSAD
        switch (keyPressedLabel) {

            case 'a':

                if (startZeroX>0){
                    swapMatrixElements(startZeroX,startZeroY,startZeroX-1,startZeroY);
                    gameUi.swapItems(startZeroX,startZeroY,startZeroX-1,startZeroY);
                    startZeroX--;

                }
                soutGameMatrix();
                break;
            case 's':
                if (startZeroY<3) {
                    swapMatrixElements(startZeroX,startZeroY,startZeroX,startZeroY+1);
                    gameUi.swapItems(startZeroX,startZeroY,startZeroX,startZeroY+1);
                    startZeroY++;
                }
                soutGameMatrix();

                break;
            case 'd':
                if (startZeroX<3){
                    swapMatrixElements(startZeroX,startZeroY,startZeroX+1,startZeroY);
                    gameUi.swapItems(startZeroX,startZeroY,startZeroX+1,startZeroY);
                    startZeroX++;
               }
                soutGameMatrix();

                break;
            case 'w':
                if (startZeroY>0){
                    swapMatrixElements(startZeroX,startZeroY,startZeroX,startZeroY-1);
                    gameUi.swapItems(startZeroX,startZeroY,startZeroX,startZeroY-1);
                    startZeroY--;
                }
                soutGameMatrix();

                break;
        }
    }

    public void createStartState(int maxMoveNumber) {

        // create and fill the Array
        for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
            for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {
                gameMatrix[p][k] = num++;
            }
        }
        gameMatrix[startZeroY][startZeroX] = 0;

        // Random moves by swapping them (4 direction)
        do {
            int randomMoveDirection = random.nextInt(4);
            numberOfMoves++;

            switch (randomMoveDirection) {
                case 0:
                    finalZeroY = startZeroY;
                    finalZeroX = startZeroX;
                    finalZeroY--;
                    if (finalZeroY < 0) {
                        continue;
                    }
                    gameMatrix[startZeroY][startZeroX] = gameMatrix[finalZeroY][finalZeroX];
                    gameMatrix[finalZeroY][finalZeroX] = 0;
                    startZeroY = finalZeroY;
                    startZeroX = finalZeroX;
                    break;

                case 1:
                    finalZeroY = startZeroY;
                    finalZeroX = startZeroX;
                    finalZeroX++;
                    if (finalZeroX > GameDimensions.DISPLAY_X - 1) {
                        continue;
                    }
                    gameMatrix[startZeroY][startZeroX] = gameMatrix[finalZeroY][finalZeroX];
                    gameMatrix[finalZeroY][finalZeroX] = 0;
                    startZeroY = finalZeroY;
                    startZeroX = finalZeroX;
                    break;

                case 2:
                    finalZeroY = startZeroY;
                    finalZeroX = startZeroX;
                    finalZeroY++;
                    if (finalZeroY > GameDimensions.DISPLAY_Y - 1) {
                        continue;
                    }
                    gameMatrix[startZeroY][startZeroX] = gameMatrix[finalZeroY][finalZeroX];
                    gameMatrix[finalZeroY][finalZeroX] = 0;
                    startZeroY = finalZeroY;
                    startZeroX = finalZeroX;

                    break;

                case 3:
                    finalZeroY = startZeroY;
                    finalZeroX = startZeroX;
                    finalZeroX--;
                    if (finalZeroX < 0) {
                        continue;
                    }
                    gameMatrix[startZeroY][startZeroX] = gameMatrix[finalZeroY][finalZeroX];
                    gameMatrix[finalZeroY][finalZeroX] = 0;
                    startZeroY = finalZeroY;
                    startZeroX = finalZeroX;
                    break;
            }

        } while (numberOfMoves < maxMoveNumber);
        System.out.println(""+startZeroX+""+startZeroY);
    }
    public void soutGameMatrix(){
        for (int i=0;i<gameMatrix.length;i++){
            System.out.println("");
            for (int j=0;j<gameMatrix.length;j++){
                System.out.print(gameMatrix[j][i]+" ");
            }
        }

    }
    private void swapMatrixElements(int startZeroX, int startZeroY, int finalZeroX, int finalZeroY){
        gameMatrix[startZeroX][startZeroY] = gameMatrix[finalZeroX][finalZeroY];
        gameMatrix[finalZeroX][finalZeroY] = 0;

    }

    public Integer getMatrixElement(int x, int y) {

        return gameMatrix[x][y];
    }

    public void setGameMatrixElement(int x,int y,int matrixElement) {

        gameMatrix[x][y] = matrixElement;
    }

}




