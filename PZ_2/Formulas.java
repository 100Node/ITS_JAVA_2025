package PZ_2;
import static java.lang.Math.*;

public class Formulas {
    public static void main(String[] args) {
        double result22 = calculate22(1.23, -0.34, 0.707);
        double result23 = calculate23(-3.45, -2.34, 1.45, 0.83);
        double result24 = calculate24(0.345, -2.25, 2.65, 3.99);

        System.out.println("result formula 22: " + result22 + "\n");
        System.out.println("result formula 23: " + result23 + "\n");
        System.out.println("result formula 24: " + result24 + "\n");
    }

    public static double calculate22(double a, double b, double c) {

        double result = (4 * sinh(sqrt(abs(a / b))) + 3 * asin(c));

        return result;
    }

    public static double calculate23(double a, double b, double c, double d) {

        double result = (5 * c) / cos(a) + sqrt((sinh(abs(b) * c)) / tan(d));

        return result;
    }

    public static double calculate24(double a, double b, double c, double d) {

        double result = (cos(b) + sin(sqrt(a))) / (2 * log(c) + exp(d));

        return result;
    }

}