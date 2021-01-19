package tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleFileReader {
    public static List<String> readFile(String filename) {
        List<String> result = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                result.add(inputLine);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
