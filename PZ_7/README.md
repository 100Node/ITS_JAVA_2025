# Контрольні питання

## 1. Для чого призначений Collections Framework?
Єдина архітектура для роботи з колекціями даних у Java. Надає готові структури (List, Set, Map), алгоритми (сортування, пошук) та уніфікований API.

---

## 2. Різниця між масивами та колекціями?

| Масиви | Колекції |
|--------|----------|
| Фіксований розмір | Динамічний розмір |
| Примітиви + об'єкти | Тільки об'єкти |
| Мінімум методів | Багато методів (add, remove, sort) |
| Немає Generics | Є Generics (типобезпека) |

---

## 3. Особливі властивості списків?
-  Зберігають порядок додавання
-  Доступ по індексу `get(i)`
-  Дозволяють дублікати
-  Позиційна вставка `add(index, element)`

---

## 4. Чим списки відрізняються від масивів?
- Динамічний розмір (не треба вказувати заздалегідь)
- Тільки об'єкти (не примітиви)
- Багато вбудованих методів
- Generics для типобезпеки

---

## 5. ArrayList vs LinkedList?

| | ArrayList | LinkedList |
|-|-----------|------------|
| **Структура** | Динамічний масив | Двозв'язний список |
| **get(i)** | O(1)  | O(n) |
| **add(0, e)** | O(n) | O(1)  |
| **Пам'ять** | Менше | Більше |

**Використовуйте:**
- **ArrayList** — за замовчуванням (швидкий доступ)
- **LinkedList** — часті вставки на початку/середині

---

## 6. Що таке Iterator?
Об'єкт для послідовного перегляду колекції.

**Переваги над `for(i)`:**
- Працює для всіх колекцій (навіть без індексів: Set)
- Безпечне видалення `it.remove()`
- Не викликає ConcurrentModificationException
- Універсальний (не залежить від структури)

```java
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (it.next().equals("B")) {
        it.remove(); // Безпечно
    }
}
```

---

## 7. Типізовані vs нетипізовані колекції?

**Нетипізовані (raw):**
```java
List list = new ArrayList(); // Без <Type>
list.add("String");
list.add(123); // Можна все
String s = (String) list.get(0); // Потрібен cast
```

**Типізовані (generics):**
```java
List<String> list = new ArrayList<>();
list.add("String");
list.add(123); //  Помилка компіляції
String s = list.get(0); // Каст не потрібен
```

**Завжди використовуйте типізовані!**

---

## 8. Для чого RandomAccess?
Marker interface — позначає, що колекція підтримує швидкий доступ за індексом O(1).

-  ArrayList implements RandomAccess
-  LinkedList не implements

**Навіщо:** для оптимізації алгоритмів
```java
if (list instanceof RandomAccess) {
    for (int i = 0; i < list.size(); i++) {} // Для ArrayList
} else {
    for (T item : list) {} // Для LinkedList
}
```

---

## 9. Collection vs Collections?

| Collection | Collections |
|------------|-------------|
| **Інтерфейс** | **Утилітний клас** |
| Описує колекцію | Алгоритми для колекцій |
| `Collection<T> c = new ArrayList<>()` | `Collections.sort(list)` |
| Інстансні методи | Статичні методи |

**Аналогія:** Array vs Arrays

```java
Collection<String> c = new ArrayList<>(); // Інтерфейс
Collections.sort(c);                      // Утиліти
```