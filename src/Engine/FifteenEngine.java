package Engine;


import Game.Fifteen;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;



/**
 * Created by Taras on 21.09.2016.
 */
public class FifteenEngine implements KeyListener{

    Fifteen fifteen;
    Random random=new Random();
    int startZeroWeight=Constants.i-1;
    int startZeroHeight=Constants.j-1;
    public int finalZeroWeight;
    public int finalZeroHeight;
    int num=1;
    int r;
    int count=0;
    public int n[][];

    public FifteenEngine(Fifteen fifteen)  {
        this.fifteen=fifteen;
        n=new int[Constants.i][Constants.j];
    }



    /**
     * Class for generate starting position by random moves.
     */

    public void startPosition(int Count) {
//        create Array
//        fill Array
            for (int k = 0; k < Constants.j; k++) {
                for (int p = 0; p < Constants.i; p++) {
                    n[p][k] =num++;
                }
            }
            n[startZeroWeight][startZeroHeight] = 0;

            do {
                r = random.nextInt(4);
                count++;
                System.out.println(+r);
                if (r == 0) {
                    finalZeroWeight = startZeroWeight;
                    finalZeroHeight = startZeroHeight;
                    finalZeroWeight--;
                    if (finalZeroWeight < 0) {
                        continue;
                    }

                    n[startZeroWeight][startZeroHeight] = n[finalZeroWeight][finalZeroHeight];
                    n[finalZeroWeight][finalZeroHeight] = 0;
                    startZeroWeight = finalZeroWeight;
                    startZeroHeight = finalZeroHeight;

                }
                if (r == 1) {
                    finalZeroWeight = startZeroWeight;
                    finalZeroHeight = startZeroHeight;
                    finalZeroHeight++;
                    if (finalZeroHeight > Constants.i - 1) {
                        continue;
                    }

                    n[startZeroWeight][startZeroHeight] = n[finalZeroWeight][finalZeroHeight];
                    n[finalZeroWeight][finalZeroHeight] = 0;
                    startZeroWeight = finalZeroWeight;
                    startZeroHeight = finalZeroHeight;

                }
                if (r == 2) {
                    finalZeroWeight = startZeroWeight;
                    finalZeroHeight = startZeroHeight;
                    finalZeroWeight++;
                    if (finalZeroWeight > Constants.j - 1) {
                        continue;
                    }

                    n[startZeroWeight][startZeroHeight] = n[finalZeroWeight][finalZeroHeight];
                    n[finalZeroWeight][finalZeroHeight] = 0;
                    startZeroWeight = finalZeroWeight;
                    startZeroHeight = finalZeroHeight;

                }
                if (r == 3) {
                    finalZeroWeight = startZeroWeight;
                    finalZeroHeight = startZeroHeight;
                    finalZeroHeight--;
                    if (finalZeroHeight < 0) {
                        continue;
                    }

                    n[startZeroWeight][startZeroHeight] = n[finalZeroWeight][finalZeroHeight];
                    n[finalZeroWeight][finalZeroHeight] = 0;
                    startZeroWeight = finalZeroWeight;
                    startZeroHeight = finalZeroHeight;

                }

            } while (count<Count);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c=e.getKeyChar();

            System.out.println(""+c);
            if (c == 'w') {
                finalZeroWeight = startZeroWeight;
                finalZeroHeight = startZeroHeight;
                finalZeroWeight--;


                n[startZeroWeight][startZeroHeight] = n[finalZeroWeight][finalZeroHeight];
                n[finalZeroWeight][finalZeroHeight] = 0;
                startZeroWeight = finalZeroWeight;
                startZeroHeight = finalZeroHeight;

            }
            if (c == 'd') {
                finalZeroWeight = startZeroWeight;
                finalZeroHeight = startZeroHeight;
                finalZeroHeight++;


                n[startZeroWeight][startZeroHeight] = n[finalZeroWeight][finalZeroHeight];
                n[finalZeroWeight][finalZeroHeight] = 0;
                startZeroWeight = finalZeroWeight;
                startZeroHeight = finalZeroHeight;

            }
            if (c == 's') {
                finalZeroWeight = startZeroWeight;
                finalZeroHeight = startZeroHeight;
                finalZeroWeight++;

                n[startZeroWeight][startZeroHeight] = n[finalZeroWeight][finalZeroHeight];
                n[finalZeroWeight][finalZeroHeight] = 0;
                startZeroWeight = finalZeroWeight;
                startZeroHeight = finalZeroHeight;

            }
            if (c == 'a') {

                finalZeroWeight = startZeroWeight;
                finalZeroHeight = startZeroHeight;
                finalZeroHeight--;


                n[startZeroWeight][startZeroHeight] = n[finalZeroWeight][finalZeroHeight];
                n[finalZeroWeight][finalZeroHeight] = 0;
                startZeroWeight = finalZeroWeight;
                startZeroHeight = finalZeroHeight;

            }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}




