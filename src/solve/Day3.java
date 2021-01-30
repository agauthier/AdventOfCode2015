package solve;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.Pair;
import tools.Utils;

import static tools.Utils.FAILED;
import static tools.Utils.SUCCESS;

@SuppressWarnings("unused")
class Day3 {

    enum Direction {
        UP('^') { @Override public Pair<Integer, Integer> getPoint(Pair<Integer, Integer> fromPoint) { return Pair.of(fromPoint.getItem1(), fromPoint.getItem2() + 1); } },
        RIGHT('>') { @Override public Pair<Integer, Integer> getPoint(Pair<Integer, Integer> fromPoint) { return Pair.of(fromPoint.getItem1() + 1, fromPoint.getItem2()); } },
        DOWN('v') { @Override public Pair<Integer, Integer> getPoint(Pair<Integer, Integer> fromPoint) { return Pair.of(fromPoint.getItem1(), fromPoint.getItem2() - 1); } },
        LEFT('<') { @Override public Pair<Integer, Integer> getPoint(Pair<Integer, Integer> fromPoint) { return Pair.of(fromPoint.getItem1() - 1, fromPoint.getItem2()); } };

        private final char value;

        Direction(char value) { this.value = value; }

        public abstract Pair<Integer, Integer> getPoint(Pair<Integer, Integer> fromPoint);

        private static Direction of(char value) {
            for (Direction dir : values()) {
                if (dir.value == value) {
                    return dir;
                }
            }
            return UP;
        }
    }

    private static final List<String> fileLines = Utils.readFile("day3.txt");

    static String solveA() {
        Map<Pair<Integer, Integer>, Integer> visits = new HashMap<>();
        Pair<Integer, Integer> point = Pair.of(0, 0);
        visits.put(point, 1);
        String line = fileLines.get(0);
        for (int i = 0; i < line.length(); i++) {
            point = Direction.of(line.charAt(i)).getPoint(point);
            Integer nbVisits = visits.computeIfAbsent(point, k -> 1);
            visits.put(point, nbVisits + 1);
        }
        int nbVisitedHouses = visits.keySet().size();
        return (nbVisitedHouses == 2592 ? SUCCESS : FAILED) + "Number of houses visited: " + nbVisitedHouses;
    }

    static String solveB() {
        Map<Pair<Integer, Integer>, Integer> visits = new HashMap<>();
        Pair<Integer, Integer> pointSanta = Pair.of(0, 0);
        Pair<Integer, Integer> pointRobot = Pair.of(0, 0);
        visits.put(pointSanta, 2);
        String line = fileLines.get(0);
        for (int i = 0; i < line.length(); i++) {
            if (i % 2 == 0) {
                pointSanta = Direction.of(line.charAt(i)).getPoint(pointSanta);
                Integer nbVisits = visits.computeIfAbsent(pointSanta, k -> 1);
                visits.put(pointSanta, nbVisits + 1);
            } else {
                pointRobot = Direction.of(line.charAt(i)).getPoint(pointRobot);
                Integer nbVisits = visits.computeIfAbsent(pointRobot, k -> 1);
                visits.put(pointRobot, nbVisits + 1);
            }
        }
        int nbVisitedHouses = visits.keySet().size();
        return (nbVisitedHouses == 2360 ? SUCCESS : FAILED) + "Number of houses visited: " + nbVisitedHouses;
    }
}