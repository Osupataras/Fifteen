package com.taras.ui;

/**
 * Class created by Andrey on 9/22/16.
 * This class is representing a contract, which all user interface implementations must follow
 * in order to work with {@link com.taras.logic.FifteenEngine}
 */
public interface IUserInterface {

    /**
     * Call this method from {@link com.taras.logic.FifteenEngine} class in order to update the user interface.
     * Place all code that should update the UI, inside the implementation of this method.
     *
     * @param firstX    X coordinate of the first item to swap.
     * @param firstY    Y coordinate of the first item to swap.
     * @param secondX   X coordinate of the second item to swap.
     * @param secondY   Y coordinate of the second item to swap.
     */
    void swapItems(int firstX, int firstY, int secondX, int secondY);
    void setNewGame(int gameMatrix[][]);
}
