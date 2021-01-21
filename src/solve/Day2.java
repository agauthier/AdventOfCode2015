package solve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tools.Utils;

import static tools.Utils.FAILED;
import static tools.Utils.SUCCESS;

@SuppressWarnings("unused")
class Day2 {

    private static class Box {

        private final List<Integer> dimensions = new ArrayList<>();

        public Box(String boxStr) {
            String[] dimsStr = boxStr.split("x");
            for (String dimStr : dimsStr) {
                dimensions.add(Integer.parseInt(dimStr));
            }
            Collections.sort(dimensions);
        }

        public int surface() {
            return 2 * (dimensions.get(0) * dimensions.get(1) + dimensions.get(1) * dimensions.get(2) + dimensions.get(2) * dimensions.get(0));
        }

        public int volume() {
            return dimensions.get(0) * dimensions.get(1) * dimensions.get(2);
        }

        public int smallestSurface() {
            return dimensions.get(0) * dimensions.get(1);
        }

        public int smallestPerimeter() {
            return 2 * (dimensions.get(0) + dimensions.get(1));
        }
    }

    private static final List<String> fileLines = Utils.readFile("day2.txt");
    private static final List<Box> boxes = new ArrayList<>();
    static {
        for (String line : fileLines) {
            boxes.add(new Box(line));
        }
    }

    static String solveA() {
        int totalSurface = 0;
        for (Box box : boxes) {
            totalSurface = totalSurface + box.surface() + box.smallestSurface();
        }
        return (totalSurface == 1598415 ? SUCCESS : FAILED) + "The required surface to cover is: " + totalSurface;
    }

    static String solveB() {
        int totalRibbon = 0;
        for (Box box : boxes) {
            totalRibbon = totalRibbon + box.smallestPerimeter() + box.volume();
        }
        return (totalRibbon == 3812909 ? SUCCESS : FAILED) + "The required ribbon length is: " + totalRibbon;
    }
}