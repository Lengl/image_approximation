package algorithms;

import java.awt.image.BufferedImage;

/**
 * Created by Lengl on 03.10.2017.
 */
public class OrderedDithering implements Algorithm {
    // |0 2|
    // |3 1|
    private static final int[][] D = {{32, 160}, {224, 96}};
    private static final int DDIM = 2;

    public void apply(BufferedImage image) {
        int pixel;
        for (int row = 0; row < image.getHeight(); row++) {
            for (int col = 0; col < image.getWidth(); col++) {
                pixel = image.getRGB(col, row);
                //assuming R==G==B
                int r=(pixel>>16) & 0xff;
                image.setRGB(col, row, r < D[row % DDIM][col % DDIM] ? BLACK : WHITE);
            }
        }
    }
}
