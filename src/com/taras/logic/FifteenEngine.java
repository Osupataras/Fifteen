package com.taras.logic;

import com.taras.config.GameDimensions;
import com.taras.ui.GameUi;
import com.taras.ui.IUserInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Created by Taras on 21.09.2016.
 */
public class FifteenEngine{

    private Random random = new Random();

    private int startZeroX = GameDimensions.DISPLAY_X - 1; //Sarting position of zero button
    private int startZeroY = GameDimensions.DISPLAY_Y - 1;
    private int finalZeroX=1; //Position of target button
    private int finalZeroY=1;
    private int num = 1; //Counter used to fiil the Array
    private int numberOfMoves = 0;
    private int gameMatrix[][];
    GameUi gameUi =new GameUi();



    /**
     * Call the {@link IUserInterface#swapItems(int, int, int, int)} method of this object to
     * swap items inside the UI.
     */
    public IUserInterface iUserInterface;

    public FifteenEngine(IUserInterface userInterface)  {
        gameMatrix = new int[GameDimensions.DISPLAY_X][GameDimensions.DISPLAY_Y];
        this.iUserInterface = userInterface;
    }

    public void processPressedKey(char keyPressedLabel) {

        // Some moves by WSAD
        switch (keyPressedLabel) {

            case 'a':
                System.out.println("a");
                if (finalZeroY>0){
                finalZeroY = startZeroY;
                finalZeroX = startZeroX;
                finalZeroY--;
                gameMatrix[startZeroY][startZeroX] = gameMatrix[finalZeroY][finalZeroX];
                gameMatrix[finalZeroY][finalZeroX] = 0;
                gameUi.buttons[startZeroX][startZeroY].setText(""+gameMatrix[startZeroX][startZeroY]);
                startZeroY = finalZeroY;
                startZeroX = finalZeroX;
                iUserInterface.swapItems(startZeroX,startZeroY,finalZeroX,finalZeroY);}
                break;
            case 's':
                System.out.println("s");
                if (finalZeroX<3){
                finalZeroY = startZeroY;
                finalZeroX = startZeroX;
                finalZeroX++;
                gameMatrix[startZeroY][startZeroX] = gameMatrix[finalZeroY][finalZeroX];
                gameMatrix[finalZeroY][finalZeroX] = 0;
                gameUi.buttons[startZeroX][startZeroY].setText(""+gameMatrix[startZeroX][startZeroY]);
                startZeroY = finalZeroY;
                startZeroX = finalZeroX;
                iUserInterface.swapItems(startZeroX,startZeroY,finalZeroX,finalZeroY);}
                break;
            case 'd':
                System.out.println("d");
               if (finalZeroY<3){
                finalZeroY = startZeroY;
                finalZeroX = startZeroX;
                finalZeroY++;
                gameMatrix[startZeroY][startZeroX] = gameMatrix[finalZeroY][finalZeroX];
                gameMatrix[finalZeroY][finalZeroX] = 0;
                gameUi.buttons[startZeroX][startZeroY].setText(""+gameMatrix[startZeroX][startZeroY]);
                startZeroY = finalZeroY;
                startZeroX = finalZeroX;
                iUserInterface.swapItems(startZeroX,startZeroY,finalZeroX,finalZeroY);}
                break;
            case 'w':
                System.out.println("w");
                if (finalZeroX<0){
                finalZeroY = startZeroY;
                finalZeroX = startZeroX;
                finalZeroX--;
                gameMatrix[startZeroY][startZeroX] = gameMatrix[finalZeroY][finalZeroX];
                gameMatrix[finalZeroY][finalZeroX] = 0;
                gameUi.buttons[startZeroX][startZeroY].setText(""+gameMatrix[startZeroX][startZeroY]);
                startZeroY = finalZeroY;
                startZeroX = finalZeroX;
                iUserInterface.swapItems(startZeroX,startZeroY,finalZeroX,finalZeroY);}
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
    }

    public Integer getMatrixElement(int x, int y) {

        return gameMatrix[x][y];
    }

    public void setGameMatrixElement(int x,int y,int matrixElement) {

        gameMatrix[x][y] = matrixElement;
    }

}




