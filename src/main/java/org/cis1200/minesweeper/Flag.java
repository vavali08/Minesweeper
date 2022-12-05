package org.cis1200.minesweeper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Flag {
    public static final String IMG_FILE = "files/flag.png";

    private static BufferedImage img;


    public Flag() {
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, 25, 25, null);
    }
}
