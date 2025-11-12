# Відповіді на контрольні питання

## 1. Immutable об'єкти та String

**Immutable об'єкт** – об'єкт, стан якого неможливо змінити після створення.

**Чому String immutable:**
- Безпека (паролі, URL не можуть бути змінені)
- String Pool – економія пам'яті, різні змінні використовують один об'єкт
- Thread-safe за замовчуванням
- Кешування hashCode для швидкої роботи з колекціями

**Чому String final:**
- Щоб заборонити успадкування, яке могло б порушити незмінність

## 2. Регулярні вирази

**Regex** – шаблони для пошуку та перевірки тексту.

```java
"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"  // Email
"^\\+380\\d{9}$"                                      // Телефон
"^\\d+$"                                              // Тільки цифри
"^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(\\d{4})$"  // Дата DD.MM.YYYY
```

## 3. Різниця між «==» та «equals()»

- **==** – порівнює **посилання** (адреси об'єктів у пам'яті)
- **equals()** – порівнює **вміст** (значення) об'єктів

```java
String str1 = new String("Java");
String str2 = new String("Java");

str1 == str2        // false (різні об'єкти)
str1.equals(str2)   // true (однаковий вміст)
```

## 4. StringBuilder та StringBuffer

Для ефективної роботи зі **змінюваними** рядками (String immutable, тому повільний при частих змінах).

- **StringBuilder** – швидкий, не thread-safe, для однопоточних додатків
- **StringBuffer** – повільніший, thread-safe, для багатопоточних додатків

```java
StringBuilder sb = new StringBuilder();
sb.append("Hello").append(" ").append("World");
String result = sb.toString();
```

## 5. Видалення пробілів

**Метод `trim()`** – видаляє пробіли на початку та кінці:

```java
String str = "   Hello   ";
String result = str.trim();  // "Hello"
```

**Java 11+:** `strip()`, `stripLeading()`, `stripTrailing()` – є також методи які видаляють пробіли.