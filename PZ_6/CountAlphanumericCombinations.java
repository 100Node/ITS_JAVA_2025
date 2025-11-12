package PZ_6;

/**
 * Лабораторна робота №9 - Завдання 2
 * Підрахунок комбінацій літер та цифр у реченні
 */
public class CountAlphanumericCombinations {

    public static int countAlphanumericCombinations(String sentence) {
        if (sentence == null) {
            throw new NullPointerException("Речення не може бути null");
        }

        if (sentence.trim().isEmpty()) {
            throw new IllegalArgumentException("Речення не може бути порожнім");
        }

        // Розділяємо речення на слова (за пробілами та розділовими знаками)
        String[] words = sentence.split("[\\s+,.;:!?()\\[\\]{}\"']+");

        int count = 0;

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            if (isAlphanumericCombination(word)) {
                count++;
            }
        }

        return count;
    }

    private static boolean isAlphanumericCombination(String word) {
        boolean hasLetter = false;
        boolean hasDigit = false;

        char[] chars = word.toCharArray();

        for (char c : chars) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                return false;
            }

            if (hasLetter && hasDigit) {
                continue;
            }
        }

        // Комбінація повинна містити і літери, і цифри
        return hasLetter && hasDigit;
    }

    // Точка входу програми
    public static void main(String[] args) {

        // Виклик методу
        String demo = "The user with the nickname koala757677 this month wrote 3 times more comments than the user with the nickname croco181dile920,gfgf1 4,months ago";

        System.out.println("\nЗагальна кількість комбінацій: " + countAlphanumericCombinations(demo));
    }
}