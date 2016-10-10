package com.taras.logic;

import com.taras.config.GameDimensions;
import com.taras.ui.GameUi;
import com.taras.ui.IUserInterface;

import javax.swing.*;
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

            case 'w':

                if (startZeroX>0){
                    swapMatrixElements(startZeroX,startZeroY,startZeroX-1,startZeroY);
                    startZeroX--;
                }
                break;
            case 'd':
                if (startZeroY<3) {
                    swapMatrixElements(startZeroX,startZeroY,startZeroX,startZeroY+1);
                    startZeroY++;
                }
                break;
            case 's':
                if (startZeroX<3){
                    swapMatrixElements(startZeroX,startZeroY,startZeroX+1,startZeroY);
                    startZeroX++;
               }
                break;
            case 'a':
                if (startZeroY>0){
                    swapMatrixElements(startZeroX,startZeroY,startZeroX,startZeroY-1);
                    startZeroY--;
                }
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
                case 0 :
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

    }
// method used for swap elemetns in matrix and check for win state
    private void swapMatrixElements(int startZeroX, int startZeroY, int finalZeroX, int finalZeroY){
        gameMatrix[startZeroX][startZeroY] = gameMatrix[finalZeroX][finalZeroY];
        gameMatrix[finalZeroX][finalZeroY] = 0;
        iUserInterface.swapItems(startZeroX,startZeroY,finalZeroX,finalZeroY);
        winConforming();

    }
// method used to create new game (refill game matrix)
    public void newGame(){
        createStartState(1000);
        iUserInterface.setNewGame(gameMatrix);
    }
//getter of game matrix
    public Integer getMatrixElement(int x, int y) {

        return gameMatrix[x][y];
    }
// method for checking winning state
    public void winConforming(){
        num = 1;
        for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
            for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {
                if (getMatrixElement(p,k) == num){
                    num++;
                }
            }
        }
        if(num==GameDimensions.DISPLAY_X*GameDimensions.DISPLAY_Y){
            System.out.println(""+num);
            JOptionPane.showMessageDialog(null,"Победа!");

        }

    }

}




