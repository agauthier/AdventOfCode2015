package solve;

import java.lang.reflect.Method;

public class Problems {

    private static final String totalTimeFormat = "Total execution time: %.3f s";

    private static record Result(int day, String item, long executionTime, String result) {
        @Override public String toString() {
            return String.format("Day %2d%s - Duration: %5d ms - %s", day, item, executionTime/1000000, result);
        }
    }

    public static void main(String... args) {
        solveAll();
    }

    private static void solveAll() {
        float totalTime = 0;
        for (int i = 1; i <= 7; i++) {
            Result resultA = solve(i, "A");
            Result resultB = solve(i, "B");
            System.out.println(resultA);
            System.out.println(resultB);
            totalTime += resultA.executionTime();
            totalTime += resultB.executionTime();
        }
        System.out.printf(totalTimeFormat + "%n", totalTime/1000000000);
    }

    private static Result solve(int day, String item) {
        String resultStr = "Problem unavailable.";
        Method method = null;
        try {
            method = Class.forName("solve.Day" + day).getDeclaredMethod("solve" + item);
        } catch (Exception ignored) {}
        long startTime = System.nanoTime();
        if (method != null) {
            try {
                resultStr = method.invoke(null).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Result(day, item, System.nanoTime() - startTime, resultStr);
    }
}
