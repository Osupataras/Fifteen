package com.taras.config;

/**
 * Created by Taras on 15.10.2016.
 */
public interface IGameMatrixState {
    int[][] getGameMatrix();
    int getMatrixElement(int x, int y);
    void setGameMatrixElement(int p,int k ,int gameMatrixElement);
    void setStartZeroX(int startZeroX);
    void setStartZeroY(int startZeroY);
    void newGame();

}
