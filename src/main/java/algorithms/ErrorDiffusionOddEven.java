package algorithms;

import java.awt.image.BufferedImage;

/**
 * Created by Lengl on 03.10.2017.
 */
public class ErrorDiffusionOddEven implements Algorithm {
    public void apply(BufferedImage image) {
        int pixel;
        int error = 0;
        for (int row = 0; row < image.getHeight(); row++) {
            //if line is odd - we go left -> right
            //if line is even - we go right -> left
            for (int col = row % 2 == 0 ? 0 : image.getWidth() - 1;
                 row % 2 == 0 ? col < image.getWidth() : col >= 0;
                 col += row % 2 == 0 ? 1 : -1) {

                pixel = image.getRGB(col, row);
                //assuming R==G==B
                int r = (pixel >> 16) & 0xff;

                if (r + error < 128) {
                    error = r;
                    image.setRGB(col, row, BLACK);
                } else {
                    error = r - 255;
                    image.setRGB(col, row, WHITE);
                }
            }
        }
    }
}
