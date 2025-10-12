package PZ_4;

/**
 * Завдання 2: Знайти суму третього та найбільшого додатного елементів масиву
 * Використовується цикл for-each
 */
public class Task2_SumThirdAndMaxPositive {

    public static int sumThirdAndMaxPositive(int[] array) {
        if (array == null) {
            throw new NullPointerException("Масив не може бути null");
        }
        
        if (array.length < 3) {
            throw new IndexOutOfBoundsException("Масив повинен містити щонайменше 3 елементи");
        }
        
        int thirdElement = array[2]; // Третій елемент (індекс 2)
        
        // Шукаємо найбільший додатний елемент за допомогою for-each
        Integer maxPositive = null;
        
        for (int element : array) {
            if (element > 0) {
                if (maxPositive == null || element > maxPositive) {
                    maxPositive = element;
                }
            }
        }
        
        if (maxPositive == null) {
            throw new IllegalArgumentException("Масив не містить додатних елементів");
        }
        
        return thirdElement + maxPositive;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Завдання 2: Сума третього та найбільшого додатного елементів ===\n");
        
        // Тест 1: Звичайний масив
        int[] test1 = {5, 3, 8, 12, 7, 2, 15};
        System.out.println("Тест 1: {5, 3, 8, 12, 7, 2, 15}");
        System.out.println("Третій елемент: 8, Максимальний додатний: 15");
        System.out.println("Результат: " + sumThirdAndMaxPositive(test1));
        System.out.println("Очікується: 23 (8+15)\n");
        
        // Тест 2: Масив з від'ємними та додатними числами
        int[] test2 = {-10, -5, 4, 20, -3, 18, 1};
        System.out.println("Тест 2: {-10, -5, 4, 20, -3, 18, 1}");
        System.out.println("Третій елемент: 4, Максимальний додатний: 20");
        System.out.println("Результат: " + sumThirdAndMaxPositive(test2));
        System.out.println("Очікується: 24 (4+20)\n");
        
        // Тест 3: Третій елемент від'ємний
        int[] test3 = {1, 2, -5, 10, 8};
        System.out.println("Тест 3: {1, 2, -5, 10, 8}");
        System.out.println("Третій елемент: -5, Максимальний додатний: 10");
        System.out.println("Результат: " + sumThirdAndMaxPositive(test3));
        System.out.println("Очікується: 5 (-5+10)\n");
        
        // Тест 4: Третій елемент є максимальним додатним
        int[] test4 = {1, 2, 50, 10, 5};
        System.out.println("Тест 4: {1, 2, 50, 10, 5}");
        System.out.println("Третій елемент: 50, Максимальний додатний: 50");
        System.out.println("Результат: " + sumThirdAndMaxPositive(test4));
        System.out.println("Очікується: 100 (50+50)\n");
        
    }
}