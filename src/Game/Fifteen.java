package Game;

import Engine.FifteenEngine;
import Engine.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Fifteen {

    JButton button[] = new JButton[Constants.i*Constants.j];
    JPanel Window = new JPanel();
    BorderLayout bl = new BorderLayout();
    GridLayout gl =new GridLayout(Constants.i,Constants.j);
    JPanel pl = new JPanel();
    FifteenEngine fEng;

    int num=0;

        Fifteen(){
            fEng=new FifteenEngine(this);
            fEng.startPosition(1000);
            for (int k = 0; k < Constants.j; k++) {
                for (int p = 0; p < Constants.i; p++) {
                    button[num]=new JButton(""+fEng.n[p][k]);
                    button[num].setBackground(Color.LIGHT_GRAY);
                    if(fEng.n[p][k]==0){
                        button[num].setBackground(Color.BLACK);
                    }
                    num++;
                }
            }

            Window.setLayout(bl);
            pl.setLayout(gl);

            for (int count=0;count<Constants.i*Constants.j;count++){
                pl.add(button[count]);
            }
            Window.add("Center",pl);
            JFrame frame = new JFrame("Fifteen");
            frame.setContentPane(Window);
            frame.pack();
            frame.setVisible(true);

            for (int count=0;count<Constants.i*Constants.j;count++){
                button[count].addKeyListener(fEng);
            }

    }




    public static void main(String[] args) {

        Fifteen game=new Fifteen();


    }
}
