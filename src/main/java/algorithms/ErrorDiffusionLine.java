package algorithms;

import java.awt.image.BufferedImage;

/**
 * Created by Lengl on 03.10.2017.
 */
public class ErrorDiffusionLine implements Algorithm {
    public void apply(BufferedImage image) {
        int pixel;
        for (int row = 0; row < image.getHeight(); row++) {
            int error = 0;
            for (int col = 0; col < image.getWidth(); col++) {
                pixel = image.getRGB(col, row);
                //assuming R==G==B
                int r=(pixel>>16) & 0xff;

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
