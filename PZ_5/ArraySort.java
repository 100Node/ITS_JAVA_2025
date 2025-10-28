package PZ_5;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

    // Selection Sort - сортування вибором по спаданню
    public static void selectionSort(long[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // Знаходимо індекс максимального елемента
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            // Міняємо місцями поточний елемент з максимальним
            if (maxIndex != i) {
                long temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }

    // Insertion Sort - сортування вставками по спаданню
    public static void insertionSort(long[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            long key = arr[i];
            int j = i - 1;

            // Переміщуємо елементи, що менші за key, на одну позицію вперед
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static long[] generateRandomArray(int size, long min, long max) {
        Random random = new Random();
        long[] arr = new long[size];

        for (int i = 0; i < size; i++) {
            arr[i] = min + (long) (random.nextDouble() * (max - min));
        }

        return arr;
    }

    // Виводить масив у консоль
    public static void printArray(long[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // Тестування методів
    public static void main(String[] args) {
        System.out.println("=== ТЕСТУВАННЯ АЛГОРИТМІВ СОРТУВАННЯ ===\n");

        // Генерація тестового масиву
        int size = 10;
        long[] originalArray = generateRandomArray(size, 1, 100);

        System.out.println("Початковий масив:");
        printArray(originalArray);

        // Тестування Selection Sort
        long[] arr1 = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("\n--- Selection Sort (по спаданню) ---");
        selectionSort(arr1);
        printArray(arr1);

        // Тестування Insertion Sort
        long[] arr2 = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("\n--- Insertion Sort (по спаданню) ---");
        insertionSort(arr2);
        printArray(arr2);

    }
}