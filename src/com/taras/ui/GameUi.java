package com.taras.ui;

import com.taras.config.GameDimensions;
import com.taras.logic.FifteenEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class created by Andrey on 9/22/16.
 * This class is implementation of the {@link IUserInterface}.
 * This implementation is using Java AWT to create the matrix of JButtons.
 */
public class GameUi implements KeyListener, IUserInterface{

    private FifteenEngine gameEngine;

    private GameUi gameUi;



    // UI components
    public JButton[][] buttons;
    private JPanel window;
    private BorderLayout borderLayout;
    private GridLayout gridLayout;
    private JPanel jPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuNewGame, menuSaveGame, menuLoadGame, menuExit;
    JFrame frame = new JFrame("FifteenGameCore");



    public GameUi() {
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        buttons = new JButton[GameDimensions.DISPLAY_X ][GameDimensions.DISPLAY_Y];
        window = new JPanel();
        borderLayout = new BorderLayout();
        gridLayout = new GridLayout(GameDimensions.DISPLAY_X, GameDimensions.DISPLAY_Y);
        jPanel = new JPanel();
    }

    public void start() {
        gameEngine = new FifteenEngine(this);
        //Shuffle the deck by "count" moves
        setupUi(gameEngine);
    }

    private void setupUi(FifteenEngine fifteenEngine) {
        window.setLayout(borderLayout);
        jPanel.setLayout(gridLayout);


        for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
            for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {
                buttons[k][p]=new JButton();
                buttons[k][p].setBackground(Color.LIGHT_GRAY);
                jPanel.add(buttons[k][p]);
            }
        }

        for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
            for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {
                buttons[k][p].addKeyListener(this);
            }
        }

        menuNewGame = new JMenuItem("New game");
        menuSaveGame = new JMenuItem("Save game");
        menuLoadGame = new JMenuItem("Load game");
        menuExit = new JMenuItem("Exit");

        menu.add(menuNewGame);
        menu.add(menuSaveGame);
        menu.add(menuLoadGame);
        menu.add(menuExit);
        menuBar.add(menu);

        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        } );

        menuNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.newGame();

            }
        });


        window.add("Center", jPanel);

        frame.setContentPane(window);
        frame.setJMenuBar(menuBar);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setLocation(400, 400);
        frame.pack();
        frame.setVisible(true);

    }



    @Override
    public void swapItems(int firstX, int firstY, int secondX, int secondY) {
        buttons[firstX][firstY].setText(buttons[secondX][secondY].getText());
        buttons[firstX][firstY].setBackground(Color.LIGHT_GRAY);
        buttons[secondX][secondY].setText("");
        buttons[secondX][secondY].setBackground(Color.BLACK);
        if (gameEngine.winConfirming()){
            JOptionPane.showMessageDialog(null, "Won");
        }


    }

    @Override
    public void setNewGame(int gameMatrix[][]) {
        for (int k = 0; k < GameDimensions.DISPLAY_Y; k++) {
            for (int p = 0; p < GameDimensions.DISPLAY_X; p++) {
                Integer gameMatrixElement = gameMatrix[p][k];
                String gameMatrixElementLabel = gameMatrixElement.toString();
                buttons[p][k].setText(gameMatrixElementLabel);
                buttons[p][k].setBackground(Color.LIGHT_GRAY);

            if (gameMatrix[p][k]==0){
                buttons[p][k].setBackground(Color.BLACK);
                buttons[p][k].setText("");
            }

        }
    }
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
