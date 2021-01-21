package solve;

import java.util.List;

import tools.Utils;

import static tools.Utils.FAILED;
import static tools.Utils.SUCCESS;

@SuppressWarnings("unused")
class Day1 {

    private static final List<String> fileLines = Utils.readFile("day1.txt");

    static String solveA() {
        int floor = 0;
        String line = fileLines.get(0);
        for (int i = 0; i < line.length(); i++) {
            floor += (line.charAt(i) == '(') ? 1 : -1;
        }
        return (floor == 280 ? SUCCESS : FAILED) + "Santa has reached floor: " + floor;
    }

    static String solveB() {
        int floor = 0;
        int position = 0;
        String line = fileLines.get(0);
        for (int i = 0; i < line.length(); i++) {
            position = i + 1;
            floor += (line.charAt(i) == '(') ? 1 : -1;
            if (floor == -1) {
                break;
            }
        }
        return (position == 1797 ? SUCCESS : FAILED) + "Santa entered the basement at position: " + position;
    }
}