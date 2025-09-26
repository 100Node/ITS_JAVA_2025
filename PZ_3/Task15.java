package PZ_3;

public class Task15 {

    public static void main(String[] args) {
        printResults(0.001);
        printResults(0.0001);
        printResults(0.01);
        printResults(0.00001);
        printResults(1e-6);
        printResults(1e-8);
        printResults(0.1);

        // Error cases
        printResults(0.0);
        printResults(-0.001);
        printResults(Double.NaN);
        printResults(Double.POSITIVE_INFINITY);
    }

    public static double calculateInfiniteSeries(double epsilon) {
        if (epsilon <= 0 || Double.isNaN(epsilon) || Double.isInfinite(epsilon)) {
            throw new IllegalArgumentException("epsilon must be positive finite number, but was: " + epsilon);
        }

        double sum = 0.0;
        int i = 1;
        int maxIterations = 1000000;

        while (i <= maxIterations) {
            double denominator = (double) i * (i + 1) * (i + 2);
            double term = Math.pow(-1, i + 1) / denominator;

            // Check if precision is reached
            if (Math.abs(term) < epsilon) {
                System.out.println("Convergence reached at iteration: " + i + ", last term: " + term);
                break;
            }

            sum += term;
            i++;

            if (Double.isNaN(sum) || Double.isInfinite(sum)) {
                throw new ArithmeticException("Series calculation resulted in NaN or infinity at i=" + i);
            }
        }

        if (i > maxIterations) {
            System.out.println("Warning: Maximum iterations (" + maxIterations + ") reached, result may be inaccurate");
        }

        return sum;
    }

    static void printResults(double epsilon) {
        System.out.print("epsilon:" + epsilon + " result:");
        try {
            double result = calculateInfiniteSeries(epsilon);
            System.out.println(result);

            double exactValue = 0.25 - Math.log(2) / 2;
            System.out
                    .println("  (Exact value ≈ " + exactValue + ", difference: " + Math.abs(result - exactValue) + ")");
        } catch (Exception e) {
            System.out.println("EXCEPTION! " + e.getMessage());
        }
    }
}