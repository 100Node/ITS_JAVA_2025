package PZ_4;

import static java.lang.Math.random;

import java.util.Arrays;
import java.util.Random;

public class RandomTask {

    public static void main(String[] args) {
        printArray(200, 10, 20);
    }

    public static int[] createRandomArray(int len, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("'max' should be  greater than 'min'");
        }

        Random ran = new Random();

        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ran.nextInt(max - min) + min;
        }

        return arr;
    }

    public static void printArray(int len, int min, int max) {
        System.out.println(Arrays.toString(createRandomArray(len, min, max)));
    }
}
