package PZ_3;

public class Task3 {

    public static void main(String[] args) {
        printResults(1.0, 5);
        printResults(2.0, 10);
        printResults(0.5, 3);
        printResults(4.0, 14);
        printResults(1.0, 1);
        printResults(1.0, 15);
        printResults(0.0, 5); 
        printResults(-1.0, 5); 
        printResults(1.0, 0); 
        printResults(1.0, -1);
        printResults(Double.NaN, 5);
        printResults(1.0, 2);
    }

    public static double calculateSeriesSum(double z, int k) {
        if (Double.isNaN(z) || Double.isInfinite(z)) {
            throw new IllegalArgumentException("z must be a finite number, but was: " + z);
        }

        if (z <= 0) {
            throw new IllegalArgumentException("z must be positive, but was: " + z);
        }

        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive, but was: " + k);
        }

        if (k >= 15) {
            throw new IllegalArgumentException("k must be less than 15, but was: " + k);
        }

        double sum = 0.0;

        for (int i = 1; i <= k; i++) {
            // First term: 1/√(z·i)
            double term1 = 1.0 / Math.sqrt(z * i);

            // Second term: tg(k/i) = tan(k/i)
            double term2 = Math.tan((double) k / i);

            double currentTerm = term1 + term2;
            sum += currentTerm;

            // Check for overflow or NaN
            if (Double.isNaN(sum) || Double.isInfinite(sum)) {
                throw new ArithmeticException("Series calculation resulted in NaN or infinity at i=" + i);
            }
        }

        return sum;
    }

    static void printResults(double z, int k) {
        System.out.print("z:" + z + " k:" + k + " result:");
        try {
            System.out.println(calculateSeriesSum(z, k));
        } catch (Exception e) {
            System.out.println("EXCEPTION! " + e.getMessage());
        }
    }
}