package algorithms;

import java.awt.image.BufferedImage;

/**
 * Created by Lengl on 03.10.2017.
 */
public class FloydSteinberg implements Algorithm {
    public void apply(BufferedImage image) {
        int pixel;
        int h = image.getHeight(), w = image.getWidth();
        int[][] errors = new int[h][w];
        int tmpError;
        for (int row = 0; row < h; row++) {
            boolean isLineOdd = row % 2 == 0;
            for (int col = isLineOdd ? 0 : w - 1;
                 isLineOdd ? col < w : col >= 0;
                 col += isLineOdd ? 1 : -1) {
                pixel = image.getRGB(col, row);
                //assuming R==G==B
                int r = (pixel >> 16) & 0xff;
                if (r + errors[row][col] < 128) {
                    tmpError = (r + errors[row][col]) / 16;
                    image.setRGB(col, row, BLACK);
                } else {
                    tmpError = (r + errors[row][col] - 255) / 16;
                    image.setRGB(col, row, WHITE);
                }
                //checking for OutOfBound
                if (isLineOdd && col + 1 < w)
                    errors[row][col + 1] += tmpError * 7;
                if (!isLineOdd && col - 1 >= 0)
                    errors[row][col - 1] += tmpError * 7;
                if (row + 1 < h) {
                    if (col - 1 >= 0)
                        errors[row + 1][col - 1] += tmpError * (isLineOdd ? 3 : 1);

                    errors[row + 1][col] += tmpError * 5;

                    if (col + 1 < w)
                        errors[row + 1][col + 1] += tmpError * (isLineOdd ? 1 : 3);
                }
            }
        }
    }
}
