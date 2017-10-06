import algorithms.Algorithm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.exit;

/**
 * Created by Lengl on 03.10.2017.
 */
public class Main {
    private static String filename = null;
    private static BufferedImage img = null;
    private static String method = null;

    public static void main(String[] args) {
        try {
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Algorithm.Implemented methodToCall;
        try {
            methodToCall = Algorithm.Implemented.values()[Integer.parseInt(method)];
        } catch (NumberFormatException e) {
            methodToCall = Algorithm.Implemented.valueOf(method);
        }

        methodToCall.getAlgorithm().apply(img);

        try {
            File outFile = new File(filename + "." + methodToCall.name() + ".png");
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            ImageIO.write(img, "png", outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void prepare() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Expecting filename:");
        filename = br.readLine();
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to open file.");
            exit(0);
        }
        System.out.println("Methods availiable:\n" +
                Arrays.toString(Algorithm.Implemented.values()) +
                "\nExpecting method name or number:");
        method = br.readLine();
    }
}
