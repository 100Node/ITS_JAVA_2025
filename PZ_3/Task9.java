package PZ_3;

public class Task9 {

    public static void main(String[] args) {
        // Test cases for odd l (1,3,5,...)
        printResults(3, 1);
        printResults(5, 3);
        printResults(7, 5);

        // Test cases for even l (2,4,6,...)
        printResults(4, 2);
        printResults(9, 4);
        printResults(16, 6);

        printResults(1, 1);
        printResults(2, 2);

        // Error cases
        printResults(0, 1);
        printResults(-1, 1);
        printResults(1, 0);
        printResults(1, -1);
        printResults(2, 3);
        printResults(1, 2);
    }

    public static double calculateFunction(int t, int l) {
        if (t <= 0) {
            throw new IllegalArgumentException("t must be positive, but was: " + t);
        }

        if (l <= 0) {
            throw new IllegalArgumentException("l must be positive, but was: " + l);
        }

        double sum = 0.0;

        if (l % 2 == 1) {
            for (int i = 1; i <= t; i++) {
                if (l - i < 0) {

                    continue;
                }
                double term = Math.sqrt(l - i) / Math.sqrt(l);
                sum += term;
            }
        } else {
            double term = l / Math.sqrt(t);
            sum = t * term;
        }

        return sum;
    }

    static void printResults(int t, int l) {
        System.out.print("t:" + t + " l:" + l + " (l is " + (l % 2 == 1 ? "odd" : "even") + ") result:");
        try {
            System.out.println(calculateFunction(t, l));
        } catch (Exception e) {
            System.out.println("EXCEPTION! " + e.getMessage());
        }
    }
}