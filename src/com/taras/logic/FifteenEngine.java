package com.taras.logic;

import com.taras.config.GameDimensions;
import com.taras.ui.IUserInterface;

import java.util.Random;

/**
 * Created by Taras on 21.09.2016.
 */
public class FifteenEngine {

    private Random random = new Random();

    private int startZeroWidth = GameDimensions.DISPLAY_X - 1; //Sarting position of zero button
    private int startZeroHeight= GameDimensions.DISPLAY_Y - 1;
    private int finalZeroWidth; //Position of target button
    private int finalZeroHeight;
    private int num = 1; //Counter used to fiil the Array
    private int numberOfMoves = 0;
    private int gameMatrix[][];

    /**
     * Call the {@link IUserInterface#swapItems(int, int, int, int)} method of this object to
     * swap items inside the UI.
     */
    private IUserInterface gameUi;

    public FifteenEngine(IUserInterface userInterface)  {
        gameMatrix = new int[GameDimensions.DISPLAY_X][GameDimensions.DISPLAY_Y];
        gameUi = userInterface;
    }

    public void processPressedKey(char keyPressedLabel) {

        // Trying to do some moves by WSAD
        switch (keyPressedLabel) {
            case 'w':
                finalZeroWidth = startZeroWidth;
                finalZeroHeight = startZeroHeight;
                finalZeroWidth--;
                gameMatrix[startZeroWidth][startZeroHeight] = gameMatrix[finalZeroWidth][finalZeroHeight];
                gameMatrix[finalZeroWidth][finalZeroHeight] = 0;
                startZeroWidth = finalZeroWidth;
                startZeroHeight = finalZeroHeight;
                break;
            case 'd':
                finalZeroWidth = startZeroWidth;
                finalZeroHeight = startZeroHeight;
                finalZeroHeight++;
                gameMatrix[startZeroWidth][startZeroHeight] = gameMatrix[finalZeroWidth][finalZeroHeight];
                gameMatrix[finalZeroWidth][finalZeroHeight] = 0;
                startZeroWidth = finalZeroWidth;
                startZeroHeight = finalZeroHeight;
                break;
            case 's':
                finalZeroWidth = startZeroWidth;
                finalZeroHeight = startZeroHeight;
                finalZeroWidth++;
                gameMatrix[startZeroWidth][startZeroHeight] = gameMatrix[finalZeroWidth][finalZeroHeight];
                gameMatrix[finalZeroWidth][finalZeroHeight] = 0;
                startZeroWidth = finalZeroWidth;
                startZeroHeight = finalZeroHeight;
                break;
            case 'a':
                finalZeroWidth = startZeroWidth;
                finalZeroHeight = startZeroHeight;
                finalZeroHeight--;
                gameMatrix[startZeroWidth][startZeroHeight] = gameMatrix[finalZeroWidth][finalZeroHeight];
                gameMatrix[finalZeroWidth][finalZeroHeight] = 0;
                startZeroWidth = finalZeroWidth;
                startZeroHeight = finalZeroHeight;
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
        gameMatrix[startZeroWidth][startZeroHeight] = 0;

        // Random moves by swapping them (4 direction)
        do {
            int randomMoveDirection = random.nextInt(4);
            numberOfMoves++;

            switch (randomMoveDirection) {
                case 0:
                    finalZeroWidth = startZeroWidth;
                    finalZeroHeight = startZeroHeight;
                    finalZeroWidth--;
                    if (finalZeroWidth < 0) {
                        continue;
                    }

                    gameMatrix[startZeroWidth][startZeroHeight] = gameMatrix[finalZeroWidth][finalZeroHeight];
                    gameMatrix[finalZeroWidth][finalZeroHeight] = 0;
                    startZeroWidth = finalZeroWidth;
                    startZeroHeight = finalZeroHeight;
                    break;

                case 1:
                    finalZeroWidth = startZeroWidth;
                    finalZeroHeight = startZeroHeight;
                    finalZeroHeight++;
                    if (finalZeroHeight > GameDimensions.DISPLAY_X - 1) {
                        continue;
                    }

                    gameMatrix[startZeroWidth][startZeroHeight] = gameMatrix[finalZeroWidth][finalZeroHeight];
                    gameMatrix[finalZeroWidth][finalZeroHeight] = 0;
                    startZeroWidth = finalZeroWidth;
                    startZeroHeight = finalZeroHeight;
                    break;

                case 2:
                    finalZeroWidth = startZeroWidth;
                    finalZeroHeight = startZeroHeight;
                    finalZeroWidth++;
                    if (finalZeroWidth > GameDimensions.DISPLAY_Y - 1) {
                        continue;
                    }

                    gameMatrix[startZeroWidth][startZeroHeight] = gameMatrix[finalZeroWidth][finalZeroHeight];
                    gameMatrix[finalZeroWidth][finalZeroHeight] = 0;
                    startZeroWidth = finalZeroWidth;
                    startZeroHeight = finalZeroHeight;

                    break;

                case 3:
                    finalZeroWidth = startZeroWidth;
                    finalZeroHeight = startZeroHeight;
                    finalZeroHeight--;
                    if (finalZeroHeight < 0) {
                        continue;
                    }

                    gameMatrix[startZeroWidth][startZeroHeight] = gameMatrix[finalZeroWidth][finalZeroHeight];
                    gameMatrix[finalZeroWidth][finalZeroHeight] = 0;
                    startZeroWidth = finalZeroWidth;
                    startZeroHeight = finalZeroHeight;
                    break;
            }

        } while (numberOfMoves < maxMoveNumber);
    }

    public Integer getMatrixElement(int x, int y) {

        return gameMatrix[x][y];

    }

}




