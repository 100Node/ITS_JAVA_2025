# Контрольні питання

## 1. Чим інтерфейс Set відрізняється від Collection та List?

| | Collection | List | Set |
|-|------------|------|-----|
| **Дублікати** | Залежить від реалізації |  Дозволені |  Заборонені |
| **Порядок** | Не гарантується |  Зберігається | Залежить (HashSet - ні, LinkedHashSet - так) |
| **Індекси** | Немає |  Є `get(i)` |  Немає |
| **null** | Залежить |  Можна кілька | Один або жодного |

```java
List<String> list = new ArrayList<>();
list.add("A");
list.add("A"); // OK - дублікат дозволений
list.get(0);   // OK - доступ по індексу

Set<String> set = new HashSet<>();
set.add("A");
set.add("A"); // Додається лише один раз
set.get(0);   //  ПОМИЛКА - немає такого методу
```

**Set** — це Collection, яка гарантує унікальність елементів.

---

## 2. HashSet vs TreeSet?

| | HashSet | TreeSet |
|-|---------|---------|
| **Структура** | Хеш-таблиця | Червоно-чорне дерево |
| **Порядок** |  Не зберігається |  Відсортовані |
| **Продуктивність** | O(1) add/remove/contains | O(log n) |
| **null** |  Один null |  Заборонено |
| **Вимоги** | `equals()` + `hashCode()` | `Comparable` або `Comparator` |

```java
Set<Integer> hashSet = new HashSet<>();
hashSet.add(3);
hashSet.add(1);
hashSet.add(2);
System.out.println(hashSet); // [1, 2, 3] або [3, 1, 2] - невизначено

Set<Integer> treeSet = new TreeSet<>();
treeSet.add(3);
treeSet.add(1);
treeSet.add(2);
System.out.println(treeSet); // [1, 2, 3] - завжди відсортовано
```

**Використовуйте:**
- **HashSet** — за замовчуванням (швидше)
- **TreeSet** — коли потрібне сортування

---

## 3. Що таке хешування, хеш, хеш-код, хеш-функція?

**Хешування** — процес перетворення об'єкта на число (хеш-код) для швидкого пошуку.

**Хеш-функція** — функція, яка перетворює об'єкт → число
```java
public int hashCode() { ... }
```

**Хеш-код (хеш)** — результат роботи хеш-функції (ціле число)
```java
"Hello".hashCode(); // 69609650
```

**Приклад:**
```java
class Person {
    String name;
    int age;
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age); // Хеш-функція
    }
}

Person p = new Person("Іван", 25);
int hash = p.hashCode(); // Хеш-код = 123456789
```

**Як працює HashSet:**
1. Викликає `hashCode()` → отримує індекс корзини (bucket)
2. Перевіряє `equals()` для елементів у цій корзині
3. Якщо немає рівного — додає

---

## 4. Вимоги до коректної хеш-функції?

**Обов'язкові правила (контракт):**

1. **Консистентність:** один об'єкт → завжди один хеш-код (протягом виконання програми)
   ```java
   int h1 = obj.hashCode();
   int h2 = obj.hashCode();
   // h1 == h2 ЗАВЖДИ
   ```

2. **Узгодженість з equals():**
   ```java
   if (obj1.equals(obj2)) {
       obj1.hashCode() == obj2.hashCode(); // ОБОВ'ЯЗКОВО
   }
   ```

3. **Зворотне НЕ обов'язкове:**
   ```java
   // Різні об'єкти МОЖУТЬ мати однаковий хеш (колізія)
   obj1.hashCode() == obj2.hashCode() 
   // НЕ означає obj1.equals(obj2)
   ```

** Порушення контракту:**
```java
class Bad {
    int value;
    
    @Override
    public boolean equals(Object o) {
        return this.value == ((Bad)o).value;
    }
    
    //  ПОМИЛКА - не перевизначили hashCode()!
}

Bad b1 = new Bad(5);
Bad b2 = new Bad(5);
b1.equals(b2); // true
b1.hashCode() != b2.hashCode(); //  Порушення контракту!

HashSet<Bad> set = new HashSet<>();
set.add(b1);
set.contains(b2); // false - НЕ ПРАЦЮЄ!
```

---

## 5. Властивості хорошої хеш-функції?

1. **Рівномірний розподіл** — мінімум колізій
   ```java
   //  ПОГАНО - всі об'єкти в одну корзину
   public int hashCode() { return 42; }
   
   //  ДОБРЕ - рівномірний розподіл
   public int hashCode() { return Objects.hash(field1, field2); }
   ```

2. **Швидкість** — O(1), не повинна бути складною

3. **Використання всіх значущих полів:**
   ```java
   class Person {
       String name;
       int age;
       String hobby; // Не впливає на рівність
       
       @Override
       public int hashCode() {
           return Objects.hash(name, age); // Тільки значущі поля
       }
   }
   ```

4. **Детермінованість** — не випадкова, не залежить від часу/пам'яті

---

## 6. Критерії об'єктів для зберігання в HashSet?

**Обов'язково перевизначити:**

1. **`equals()`** — для порівняння
2. **`hashCode()`** — консистентний з equals()

```java
class Actor {
    String name;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(name, actor.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

Set<Actor> actors = new HashSet<>();
actors.add(new Actor("Том Генкс"));
actors.add(new Actor("Том Генкс")); // Не додасться - дублікат
System.out.println(actors.size()); // 1
```

** Без перевизначення:**
```java
class Bad {
    String name;
    // Не перевизначили equals() та hashCode()
}

Set<Bad> set = new HashSet<>();
set.add(new Bad("Іван"));
set.add(new Bad("Іван")); // Додасться - вважаються різними!
System.out.println(set.size()); // 2 - ПОМИЛКА!
```

---

## 7. Переваги/недоліки HashSet vs TreeSet?

| Критерій | HashSet | TreeSet |
|----------|---------|---------|
| **Швидкість** |  O(1) |  O(log n) |
| **Сортування** |  Немає |  Автоматичне |
| **Пам'ять** |  Менше |  Більше (дерево) |
| **null** |  Один null |  Заборонено |
| **Вимоги** | equals/hashCode |  Comparable |
| **Range операції** |  Немає |  `subSet()`, `headSet()` |

**HashSet:**
-  Швидший (за замовчуванням)
-  Простіший
-  Немає порядку

**TreeSet:**
-  Відсортовані елементи
-  Навігаційні методи (`first()`, `last()`, `higher()`)
-  Повільніший
-  Не підтримує null

---

## 8. Size vs Capacity?

**Size** — кількість реально збережених елементів
**Capacity** — розмір внутрішнього масиву (скільки може вміститись)

```java
List<String> list = new ArrayList<>(100); // capacity = 100
list.add("A");
list.add("B");

System.out.println(list.size()); // 2 - size
// capacity = 100 (внутрішній масив)
```

### Чи може size > capacity?
**НІ, НІКОЛИ!** Capacity автоматично збільшується при потребі.

### Чи може capacity > size?
**ТАК, ЗАВЖДИ (або дорівнює)** — це нормально.

```java
ArrayList<Integer> list = new ArrayList<>(10); // capacity = 10
list.add(1); // size = 1, capacity = 10

for (int i = 0; i < 100; i++) {
    list.add(i);
}
// size = 100, capacity = 160 (автоматично розширився)
```

**Коли capacity > size:**
- Початкова ініціалізація
- Після видалення елементів
- Після розширення (збільшується з запасом)

---

## 9. Що таке load factor?

**Load factor** — коефіцієнт завантаження, який визначає, коли розширювати хеш-таблицю.

**За замовчуванням = 0.75** (75%)

```java
HashSet<String> set = new HashSet<>(16, 0.75f);
// capacity = 16
// load factor = 0.75
// Розшириться коли size >= 16 * 0.75 = 12
```

### Як впливає:

**Load factor 0.75 (за замовчуванням):**
- ⚖️ Баланс пам'ять/швидкість

**Load factor 1.0:**
-  Економія пам'яті
-  Більше колізій → повільніше

**Load factor 0.5:**
-  Менше колізій → швидше
-  Більше пам'яті

```java
// Погана практика - занадто великий load factor
HashSet<String> bad = new HashSet<>(16, 0.99f);
// Розшириться тільки при 15 елементах
// Багато колізій → повільна робота

// Погана практика - занадто малий load factor
HashSet<String> wasteful = new HashSet<>(16, 0.1f);
// Розшириться вже при 1-2 елементах
// Марнування пам'яті
```

---

## 10. Що таке асоціативний масив? Ключ vs Значення?

**Асоціативний масив (Map)** — структура для зберігання пар "ключ → значення".

```java
Map<String, Integer> ages = new HashMap<>();
//     ключ   значення
ages.put("Іван", 25);
ages.put("Марія", 30);

int age = ages.get("Іван"); // 25
```

### Ключ vs Значення:

| | Ключ | Значення |
|-|------|----------|
| **Унікальність** |  Унікальні |  Можуть повторюватись |
| **Пошук** | По ключу шукаємо | Значення отримуємо |
| **null** | Один null (HashMap) | Багато null |
| **Вимоги** | equals/hashCode | Немає |

```java
Map<String, String> map = new HashMap<>();
map.put("key1", "value1");
map.put("key2", "value1"); //  OK - значення повторюються
map.put("key1", "value2"); //  OK - перезаписує старе значення

//  Ключі повторюватись не можуть
```

---

## 11. Чи можуть бути однакові ключі або значення?

### Ключі: **НІ, лише унікальні**
```java
Map<String, Integer> map = new HashMap<>();
map.put("Іван", 25);
map.put("Іван", 30); // Перезаписує попереднє значення

System.out.println(map.size()); // 1 (не 2!)
System.out.println(map.get("Іван")); // 30
```

### Значення: **ТАК, можуть повторюватись**
```java
Map<String, Integer> ages = new HashMap<>();
ages.put("Іван", 25);
ages.put("Петро", 25);  //  OK
ages.put("Марія", 25);  //  OK

System.out.println(ages.size()); // 3
```

---

## 12. Неімутабельні (мутабельні) об'єкти в HashSet/HashMap?

**Проблема:** Якщо змінити об'єкт після додавання → змінюється hashCode() → об'єкт губиться!

###  Мутабельний об'єкт як ключ:
```java
class BadKey {
    int value;
    
    @Override
    public int hashCode() {
        return value; // Залежить від value
    }
}

BadKey key = new BadKey(5);
Map<BadKey, String> map = new HashMap<>();
map.put(key, "Data");

key.value = 10; //  ЗМІНИЛИ КЛЮЧ!

// Тепер не знайдемо:
map.get(key); // null - ВТРАЧЕНО!
map.containsKey(key); // false
```

### У HashSet:
```java
Set<BadKey> set = new HashSet<>();
BadKey obj = new BadKey(5);
set.add(obj);

obj.value = 10; // Змінили

set.contains(obj); // false - не знайде!
set.size(); // 1 - але елемент втрачений
```

### У HashMap як значення:
```java
Map<String, BadKey> map = new HashMap<>();
BadKey value = new BadKey(5);
map.put("key", value);

value.value = 10; //  OK - значення можна міняти
map.get("key"); //  Працює - значення не використовується для пошуку
```

**Висновок:**
-  Мутабельні ключі → катастрофа
-  Мутабельні значення → нормально
-  Використовуйте імутабельні ключі (String, Integer, enum)

---

## 13. Об'єкти без Comparable у TreeMap?

**НІ** (або потрібен Comparator).

```java
class Person {
    String name;
    // Не implements Comparable
}

//  ПОМИЛКА - ClassCastException
TreeMap<Person, String> map1 = new TreeMap<>();
map1.put(new Person("Іван"), "data"); // Crash!

//  OK - надаємо Comparator
TreeMap<Person, String> map2 = new TreeMap<>(
    (p1, p2) -> p1.name.compareTo(p2.name)
);
map2.put(new Person("Іван"), "data"); // Працює
```

---

## 14. Об'єкти без Comparable у HashMap?

**ТАК, можна!** Comparable не потрібен для HashMap.

```java
class Person {
    String name;
    // Не implements Comparable - OK!
    
    @Override
    public boolean equals(Object o) { ... }
    
    @Override
    public int hashCode() { ... }
}

HashMap<Person, String> map = new HashMap<>();
map.put(new Person("Іван"), "data"); //  Працює
```

**Comparable не впливає на HashMap:**
- HashMap використовує **hashCode()** та **equals()**
- Comparable потрібен тільки для **TreeMap/TreeSet**