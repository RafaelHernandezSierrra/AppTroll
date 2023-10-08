package appTroll;				

import java.awt.*;
import java.util.Random;

public class appTroll {

    public static void main(String[] args) {
        Toolkit.getDefaultToolkit().sync();

        int nWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 1;
        int nHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 1;

        int i = 100;
        int band = 0;
        int j = 100;
        int band2 = 0;
        int band3 = 0;
        while (true) {
            if (i >= 100 && j < nHeight) {
                j++;
                i++;
                setMousePosition(i, j);
            }

            if (j >= nHeight || band == i) {
                band = 1;
                i = i + 1;
                j = j - 2;
                setMousePosition(i, j);
            }

            if (i >= nWidth || band2 == 1) {
                band = 0;
                band2 = 1;
                j = j - 2;
                i = i - 2;
                setMousePosition(i, j);
            }
            if (j <= 0 || band3 == i) {
                band2 = 0;
                band3 = 1;
                j = j + 2;
                i = i - 2;
                setMousePosition(i, j);
                if (j >= nHeight) {
                    band = 1;
                    band3 = 0;
                }
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setMousePosition(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
