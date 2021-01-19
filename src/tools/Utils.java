package tools;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Utils {

    public static final String SUCCESS = "Success: ";
    public static final String FAILED = "Failed: ";

    private static String filepath;
    static {
        try {
            filepath = new File(".").getCanonicalPath() + "\\src\\input\\";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Utils() {}

    public static List<String> readFile(String filename) {
        return SimpleFileReader.readFile(filepath + filename);
    }
}
