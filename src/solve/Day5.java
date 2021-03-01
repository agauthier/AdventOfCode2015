package solve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import tools.Utils;

import static tools.Utils.FAILED;
import static tools.Utils.SUCCESS;

//@SuppressWarnings("unused")
class Day5 {

    private static final List<String> fileLines = Utils.readFile("day5.txt");

    static String solveA() {
        List<String> lines = new ArrayList<>(fileLines);
        Pattern vowelMatch = Pattern.compile("(.*[aeiou].*){3,}");
        Pattern dblLetterMatch = Pattern.compile(".*([abcdefghijklmnopqrstuvwxyz])\\1.*");
        Pattern illegalLetterMatch = Pattern.compile(".*(ab|cd|pq|xy).*");
        lines.removeIf(line -> !vowelMatch.matcher(line).matches() || !dblLetterMatch.matcher(line).matches() || illegalLetterMatch.matcher(line).matches());
        return (lines.size() == 236 ? SUCCESS : FAILED) + "Number of nice strings: " + lines.size();
    }

    static String solveB() {
        return (1 == 0 ? SUCCESS : FAILED) + "";
    }
}