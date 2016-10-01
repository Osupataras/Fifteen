package com.taras.ui;

import com.taras.config.GameDimensions;
import com.taras.logic.FifteenEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class created by Andrey on 9/22/16.
 * This class is implementation of the {@link IUserInterface}.
 * This implementation is using Java AWT to create the matrix of JButtons.
 */
public class GameUi implements KeyListener, IUserInterface{

    private FifteenEngine gameEngine;

    private int num = 0;

    // UI components
    private JButton[] buttons;
    private JPanel window;
    private BorderLayout borderLayout;
    private GridLayout gridLayout;
    private JPanel jPanel;

    public GameUi() {
        buttons = new JButton[GameDimensions.DISPLAY_X * GameDimensions.DISPLAY_Y];
        window = new JPanel();
        borderLayout = new BorderLayout();
        gridLayout = new GridLayout(GameDimensions.DISPLAY_X, GameDimensions.DISPLAY_Y);
        jPanel = new JPanel();
    }

    public void start() {
        gameEngine = new FifteenEngine(this);
        //Shuffle the deck by "count" moves
        gameEngine.createStartState(1000);

        setupUi(gameEngine);
    }

    private void setupUi(FifteenEngine fifteenEngine) {
        int gameDimension = GameDimensions.DISPLAY_X * GameDimensions.DISPLAY_Y;

        window.setLayout(borderLayout);
        jPanel.setLayout(gridLayout);

        for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
            for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {

                Integer matrixElement = fifteenEngine.getMatrixElement(p, k);
                String matrixElementLabel = matrixElement.toString();

                buttons[num]=new JButton(matrixElementLabel);
                buttons[num].setBackground(Color.LIGHT_GRAY);

                if (matrixElement==0){
                    buttons[num].setBackground(Color.BLACK);
                }
                jPanel.add(buttons[num]);

                num++;
            }
        }

        for (int count = 0; count < gameDimension; count++){
            buttons[count].addKeyListener(this);
        }

        window.add("Center", jPanel);
        JFrame frame = new JFrame("FifteenGameCore");
        frame.setContentPane(window);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void swapItems(int firstX, int firstY, int secondX, int secondY) {
        gameEngine.setGameMatrixElement(firstX,firstY,gameEngine.getMatrixElement(secondX,secondY));
        gameEngine.setGameMatrixElement(secondX,secondY,0);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameEngine.processPressedKey(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
