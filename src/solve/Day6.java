package solve;

import java.util.List;

import tools.Utils;

import static tools.Utils.FAILED;
import static tools.Utils.SUCCESS;

//@SuppressWarnings("unused")
class Day6 {

    private static class Instruction {
        private enum Op {
            TURN_ON("on") {
                @Override void executeWrong(int[][] lights, int x, int y) { lights[y][x] = 1; }
                @Override void executeRight(int[][] lights, int x, int y) { lights[y][x]++; } },
            TURN_OFF("off") {
                @Override void executeWrong(int[][] lights, int x, int y) { lights[y][x] = 0; }
                @Override void executeRight(int[][] lights, int x, int y) { lights[y][x] = Math.max(lights[y][x] - 1, 0); } },
            TOGGLE("toggle") {
                @Override void executeWrong(int[][] lights, int x, int y) { lights[y][x] = lights[y][x] == 0 ? 1 : 0; }
                @Override void executeRight(int[][] lights, int x, int y) { lights[y][x] += 2; } };

            private final String name;

            Op(String name) {
                this.name = name;
            }

            abstract void executeWrong(int[][] lights, int x, int y);
            abstract void executeRight(int[][] lights, int x, int y);

            private static Op of(String name) {
                for (Op op : values()) {
                    if (op.name.equals(name)) {
                        return op;
                    }
                }
                throw new IllegalArgumentException("Invalid op name: " + name);
            }
        }

        private final Op op;
        private final int xs, ys, xe, ye;

        Instruction(String instructionStr) {
            int startIdx = 0;
            String[] tokens = instructionStr.split(" ");
            if (tokens[0].equals("turn")) {
                startIdx++;
            }
            this.op = Op.of(tokens[startIdx]);
            String[] firstCoords = tokens[startIdx + 1].split(",");
            this.xs = Integer.parseInt(firstCoords[0]);
            this.ys = Integer.parseInt(firstCoords[1]);
            String[] secondCoords = tokens[startIdx + 3].split(",");
            this.xe = Integer.parseInt(secondCoords[0]);
            this.ye = Integer.parseInt(secondCoords[1]);
        }

        public void execute(int[][] lights, boolean wrong) {
            for (int y = ys; y <= ye; y++) {
                for (int x = xs; x <= xe; x++) {
                    if (wrong) {
                        op.executeWrong(lights, x, y);
                    } else {
                        op.executeRight(lights, x, y);
                    }
                }
            }
        }
    }

    private static final List<String> fileLines = Utils.readFile("day6.txt");

    static String solveA() {
        int[][] lights = new int[1000][1000];
        for (String line : fileLines) {
            Instruction instruction = new Instruction(line);
            instruction.execute(lights, true);
        }
        int nbLitLights = countLitLights(lights);
        return (nbLitLights == 377891 ? SUCCESS : FAILED) + "Number of lit lights: " + nbLitLights;
    }

    static String solveB() {
        int[][] lights = new int[1000][1000];
        for (String line : fileLines) {
            Instruction instruction = new Instruction(line);
            instruction.execute(lights, false);
        }
        int nbLitLights = countLitLights(lights);
        return (nbLitLights == 14110788 ? SUCCESS : FAILED) + "Number of lit lights: " + nbLitLights;
    }

    private static int countLitLights(int[][] lights) {
        int nbLights = 0;
        for (int[] row : lights) {
            for (int light : row) {
                nbLights += light;
            }
        }
        return nbLights;
    }
}