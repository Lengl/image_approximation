package algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Created by Lengl on 03.10.2017.
 */
public interface Algorithm {
    enum Implemented {
        THRESHOLDING(new Thresholding()),
        RANDOM_DITHERING(new RandomDithering()),
        ORDERED_DITHERING(new OrderedDithering()),
        ERROR_DIFFUSION_LINE(new ErrorDiffusionLine()),
        ERROR_DIFFUSION_ODD_EVEN(new ErrorDiffusionOddEven()),
        FLOYD_STEINBERG(new FloydSteinberg());
        Implemented(Algorithm algorithm) {
            this.algorithm = algorithm;
        }
        private final Algorithm algorithm;
        public Algorithm getAlgorithm() {
            return this.algorithm;
        }
    }
    int WHITE = Color.WHITE.getRGB();
    int BLACK = Color.BLACK.getRGB();
    void apply(BufferedImage image);
}
