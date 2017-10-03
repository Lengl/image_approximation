package algorithms;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Lengl on 03.10.2017.
 */
public class RandomDithering implements Algorithm {
    private Random random = new Random();
    public void apply(BufferedImage image) {
        int pixel;
        for (int row = 0; row < image.getHeight(); row++) {
            for (int col = 0; col < image.getWidth(); col++) {
                pixel = image.getRGB(col, row);
                //assuming R==G==B
                int r = (pixel>>16) & 0xff;
                image.setRGB(col, row,
                        //r < threshold
                        r < random.nextInt(255) ? BLACK : WHITE
                );
            }
        }
    }
}
