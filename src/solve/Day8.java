package solve;

import java.util.List;

import tools.Utils;

import static tools.Utils.FAILED;
import static tools.Utils.SUCCESS;

@SuppressWarnings("unused")
class Day8 {

    private static final List<String> fileLines = Utils.readFile("day8.txt");

    static String solveA() {
        int charDiffCount = 0;
        for (String line : fileLines) {
            String[] tokens = line.split("\\\\x[0-9a-f]{2}|\\\\\"|\\\\\\\\");
            int memCount = -3;
            for (String token : tokens) {
                memCount += token.length() + 1;
            }
            charDiffCount += line.length();
            charDiffCount -= memCount;
        }
        return (charDiffCount == 1350 ? SUCCESS : FAILED) + "The character diff count is: " + charDiffCount;
    }

    static String solveB() {
        int charDiffCount = 0;
        for (String line : fileLines) {
            charDiffCount -= line.length();
            String newLine = line.replaceAll("\\\\", "\\\\\\\\");
            newLine = newLine.replaceAll("\"", "\\\\\\\"");
            charDiffCount += newLine.length() + 2;
        }
        return (charDiffCount == 2085 ? SUCCESS : FAILED) + "The character diff count is: " + charDiffCount;
    }
}