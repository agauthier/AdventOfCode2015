package solve;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

import tools.Utils;

import static tools.Utils.FAILED;
import static tools.Utils.SUCCESS;

@SuppressWarnings("unused")
class Day4 {

    private static final List<String> fileLines = Utils.readFile("day4.txt");

    static String solveA() {
        int i = 1;
        String input = fileLines.get(0);
        while (true) {
            String toHash = input + i;
            byte[] hash = DigestUtils.md5(toHash);
            if (hash[0] == 0 && hash[1] == 0 && hash[2] > 0 && hash[2] < 16) {
                break;
            } else {
                i++;
            }
        }
        return (i == 117946 ? SUCCESS : FAILED) + "Lowest integer for a hash with 5 leading 0s: " + i;
    }

    static String solveB() {
        int i = 1;
        String input = fileLines.get(0);
        while (true) {
            String toHash = input + i;
            byte[] hash = DigestUtils.md5(toHash);
            if (hash[0] == 0 && hash[1] == 0 && hash[2] == 0 && hash[3] > 15) {
                break;
            } else {
                i++;
            }
        }
        return (i == 3938038 ? SUCCESS : FAILED) + "Lowest integer for a hash with 6 leading 0s: " + i;
    }
}