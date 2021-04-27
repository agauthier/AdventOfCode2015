package solve;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import tools.Utils;

import static tools.Utils.FAILED;
import static tools.Utils.SUCCESS;

@SuppressWarnings("unused")
class Day7 {

    private enum Gate {
        ID { @Override int apply(int in1, int in2) { return in1 & 0xffff; } },
        AND { @Override int apply(int in1, int in2) { return (in1 & in2) & 0xffff; } },
        OR { @Override int apply(int in1, int in2) { return (in1 | in2) & 0xffff; } },
        NOT { @Override int apply(int in1, int in2) { return (~in1) & 0xffff; } },
        LSHIFT { @Override int apply(int in1, int in2) { return (in1 << in2) & 0xffff; } },
        RSHIFT { @Override int apply(int in1, int in2) { return (in1 >> in2) & 0xffff; } };

        abstract int apply(int in1, int in2);
    }

    private static class GateOp {

        private final String wire;
        private final Gate gate;
        private final String in1;
        private final String in2;

        GateOp(String wire, String opStr) {
            this.wire = wire;
            String[] tokens = opStr.split(" ");
            if (tokens.length == 1) {
                gate = Gate.valueOf("ID");
                in1 = tokens[0];
                in2 = null;
            } else if (tokens.length == 2) {
                gate = Gate.valueOf(tokens[0]);
                in1 = tokens[1];
                in2 = null;
            } else {
                gate = Gate.valueOf(tokens[1]);
                in1 = tokens[0];
                in2 = tokens[2];
            }
        }

        int apply() {
            Integer result = results.get(wire);
            if (result == null) {
                Pattern intMatch = Pattern.compile("[0-9]+");
                int val1 = intMatch.matcher(in1).matches() ? Integer.parseInt(in1) : gateOps.get(in1).apply();
                int val2 = (in2 != null) ? (intMatch.matcher(in2).matches() ? Integer.parseInt(in2) : gateOps.get(in2).apply()) : 0;
                result = gate.apply(val1, val2);
                results.put(wire, result);
            }
            return result;
        }
    }

    private static final List<String> fileLines = Utils.readFile("day7.txt");
    private static final Map<String, Integer> results = new HashMap<>();
    private static final Map<String, GateOp> gateOps = new HashMap<>();
    static {
        for (String line : fileLines) {
            String[] tokens = line.split(" -> ");
            gateOps.put(tokens[1], new GateOp(tokens[1], tokens[0]));
        }
    }

    static String solveA() {
        int wireA = gateOps.get("a").apply();
        return (wireA == 3176 ? SUCCESS : FAILED) + "The value of wireA is: " + wireA;
    }

    static String solveB() {
        int wireA = results.get("a");
        results.clear();
        results.put("b", wireA);
        wireA = gateOps.get("a").apply();
        return (wireA == 14710 ? SUCCESS : FAILED) + "The value of wireA is: " + wireA;
    }
}