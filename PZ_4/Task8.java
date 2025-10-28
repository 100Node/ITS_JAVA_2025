package PZ_4;

/**
 * Завдання 1: Знайти суму елементів, що кратні 3
 * Використовується цикл for
 */
public class Task1_SumMultiplesOfThree {

    public static int sumMultiplesOfThree(int[] array) {
        if (array == null) {
            throw new NullPointerException("Масив не може бути null");
        }

        int sum = 0;

        // Використовуємо цикл for
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 3 == 0) {
                sum += array[i];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 1: Сума елементів, кратних 3 ===\n");

        // Тест 1: Звичайний масив
        int[] test1 = { 9, 3, 5, 0, 12, 6 };
        System.out.println("Тест 1: {3, 5, 6, 9, 12, 15, 7, 18}");
        System.out.println("Результат: " + sumMultiplesOfThree(test1));
        System.out.println("Очікується: 63 (3+6+9+12+15+18)\n");

        // Тест 2: Масив з від'ємними числами
        int[] test2 = { -9, -3, 5, 0, 12, -6 };
        System.out.println("Тест 2: {-9, -3, 5, 0, 12, -6}");
        System.out.println("Результат: " + sumMultiplesOfThree(test2));
        System.out.println("Очікується: -6 (-9-3+0+12-6)\n");

        // Тест 3: Порожній масив
        int[] test3 = {};
        System.out.println("Тест 3: {} (порожній масив)");
        System.out.println("Результат: " + sumMultiplesOfThree(test3));
        System.out.println("Очікується: 0\n");

        // Тест 4: Масив без елементів, кратних 3
        int[] test4 = { 1, 2, 4, 5, 7, 8 };
        System.out.println("Тест 4: {1, 2, 4, 5, 7, 8} (без кратних 3)");
        System.out.println("Результат: " + sumMultiplesOfThree(test4));
        System.out.println("Очікується: 0\n");

        // Тест 5: Масив з нулем
        int[] test5 = { 0, 0, 0 };
        System.out.println("Тест 5: {0, 0, 0}");
        System.out.println("Результат: " + sumMultiplesOfThree(test5));
        System.out.println("Очікується: 0\n");

    }
}