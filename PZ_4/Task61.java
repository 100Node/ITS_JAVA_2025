import java.util.Arrays;

/**
 * Завдання 3: Сформувати масив C з масивів A та B за правилами:
 * - Якщо обидва елементи додатні: C[i] = A[i] + B[i]
 * - Якщо обидва елементи від'ємні: C[i] = A[i] * B[i]
 * - В усіх інших випадках: C[i] = 0
 */
public class Task3_CombineArrays {
    
    public static int[] combineArrays(int[] arrayA, int[] arrayB) {
        if (arrayA == null || arrayB == null) {
            throw new NullPointerException("Масиви не можуть бути null");
        }
        
        if (arrayA.length != arrayB.length) {
            throw new IllegalArgumentException(
                "Масиви повинні мати однакову довжину. " +
                "Довжина A: " + arrayA.length + ", Довжина B: " + arrayB.length
            );
        }
        
        int[] arrayC = new int[arrayA.length];
        
        for (int i = 0; i < arrayA.length; i++) {
            arrayC[i] = calculateElement(arrayA[i], arrayB[i]);
        }
        
        return arrayC;
    }
    
    // Допоміжний метод для обчислення елемента масиву C
    private static int calculateElement(int a, int b) {
        if (a > 0 && b > 0) {
            // Обидва додатні - сума
            return a + b;
        } else if (a < 0 && b < 0) {
            // Обидва від'ємні - добуток
            return a * b;
        } else {
            // Інші випадки (різні знаки або нуль) - 0
            return 0;
        }
    }
    
    // Допоміжний метод для виведення масиву
    private static void printArrays(int[] a, int[] b, int[] c) {
        System.out.println("A = " + Arrays.toString(a));
        System.out.println("B = " + Arrays.toString(b));
        System.out.println("C = " + Arrays.toString(c));
    }
    

    public static void main(String[] args) {
        System.out.println("=== Завдання 3: Формування масиву C з масивів A та B ===\n");
        
        // Тест 1: Різні комбінації знаків
        System.out.println("Тест 1: Різні комбінації знаків");
        int[] a1 = {5, -3, 4, -2, 0, 6};
        int[] b1 = {2, -4, -1, -5, 3, 7};
        int[] c1 = combineArrays(a1, b1);
        printArrays(a1, b1, c1);
        
        // Тест 2: Всі додатні
        System.out.println("Тест 2: Всі елементи додатні");
        int[] a2 = {1, 2, 3, 4, 5};
        int[] b2 = {10, 20, 30, 40, 50};
        int[] c2 = combineArrays(a2, b2);
        printArrays(a2, b2, c2);
        System.out.println("Очікується: всі елементи C - суми\n");
        
        // Тест 3: Всі від'ємні
        System.out.println("Тест 3: Всі елементи від'ємні");
        int[] a3 = {-1, -2, -3, -4};
        int[] b3 = {-5, -6, -7, -8};
        int[] c3 = combineArrays(a3, b3);
        printArrays(a3, b3, c3);
        System.out.println("Очікується: всі елементи C - добутки\n");
        
        // Тест 4: Чергування знаків
        System.out.println("Тест 4: Чергування знаків");
        int[] a4 = {1, -1, 2, -2, 3};
        int[] b4 = {-1, 1, -2, 2, -3};
        int[] c4 = combineArrays(a4, b4);
        printArrays(a4, b4, c4);
        System.out.println("Очікується: всі елементи C = 0 (різні знаки)\n");
        
        // Тест 5: Порожні масиви
        System.out.println("Тест 5: Порожні масиви");
        int[] a5 = {};
        int[] b5 = {};
        int[] c5 = combineArrays(a5, b5);
        printArrays(a5, b5, c5);
        System.out.println("Очікується: порожній масив\n");
    }
}