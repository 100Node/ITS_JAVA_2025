package PZ_8;

import java.util.*;

// Головний клас програми
public class filmsAndActorsSetMap {
    public static void main(String[] args) {
        // Створення бази даних
        MovieDatabase database = new MovieDatabase();

        // Створення акторів
        Actor actor1 = new Actor("Леонардо ДіКапріо");
        Actor actor2 = new Actor("Кейт Вінслет");
        Actor actor3 = new Actor("Том Генкс");
        Actor actor4 = new Actor("Роберт Дауні мол.");
        Actor actor5 = new Actor("Скарлетт Йоганссон");
        Actor actor6 = new Actor("Актор без фільмів");

        database.addActor(actor1);
        database.addActor(actor2);
        database.addActor(actor3);
        database.addActor(actor4);
        database.addActor(actor5);
        database.addActor(actor6);

        // Створення фільмів
        Film film1 = new Film("Титанік", 1997);
        film1.addActor(actor1);
        film1.addActor(actor2);

        Film film2 = new Film("Початок", 2010);
        film2.addActor(actor1);
        film2.addActor(actor3);

        Film film3 = new Film("Месники", 2012);
        film3.addActor(actor4);
        film3.addActor(actor5);
        film3.addActor(actor1);
        film3.addActor(actor2);
        film3.addActor(actor3);

        database.addFilm(film1);
        database.addFilm(film2);
        database.addFilm(film3);

        // Задача 1 (нетипізований ітератор)
        System.out.println("Задача 1: Чи є актор без фільмів?");
        System.out.println("Результат: " + (database.hasActorWithoutFilms() ? "Так" : "Ні"));
        System.out.println();

        // Задача 2 (типізований цикл for-each)
        System.out.println("Задача 2: Актори, з якими грав Леонардо ДіКапріо:");
        Set<Actor> coActors = database.getCoActors(actor1);
        for (Actor actor : coActors) {
            System.out.println("  - " + actor.getName());
        }
        System.out.println();

        // Задача 3 (типізований ітератор)
        System.out.println("Задача 3: Фільм з найбільшою кількістю акторів:");
        Film maxFilm = database.getFilmWithMostActors();
        if (maxFilm != null) {
            System.out.println("  Фільм: " + maxFilm.getTitle());
            System.out.println("  Кількість акторів: " + maxFilm.getActors().size());
        }
        System.out.println();

        // 1. Статистика фільмів по рокам
        System.out.println("Статистика фільмів по рокам:");
        Map<Integer, Integer> filmsByYear = database.getFilmCountByYear();
        for (Map.Entry<Integer, Integer> entry : filmsByYear.entrySet()) {
            System.out.println("  " + entry.getKey() + " рік: " + entry.getValue() + " фільм(ів)");
        }
        System.out.println();

        // 2. Кількість фільмів для кожного актора
        System.out.println("Кількість фільмів для кожного актора:");
        Map<Actor, Integer> actorFilmCount = database.getActorFilmCount();
        for (Map.Entry<Actor, Integer> entry : actorFilmCount.entrySet()) {
            System.out.println("  " + entry.getKey().getName() + ": " + entry.getValue() + " фільм(ів)");
        }
        System.out.println();

        // 3. Пошук актора по імені
        System.out.println("Пошук актора 'Том Генкс':");
        Actor found = database.findActorByName("Том Генкс");
        if (found != null) {
            System.out.println("  Знайдено: " + found);
            System.out.println("  Фільми: " + found.getFilms().size());
        }
        System.out.println();

        // 4. Найбільш продуктивні актори (з найбільшою кількістю фільмів)
        System.out.println("Топ-3 найбільш продуктивних актори:");
        Set<Actor> topActors = database.getTopActorsByFilmCount(3);
        int rank = 1;
        for (Actor actor : topActors) {
            System.out.println("  " + rank + ". " + actor.getName() +
                    " - " + actor.getFilms().size() + " фільм(ів)");
            rank++;
        }
    }
}

// Клас Актор
class Actor {
    private String name;
    private Set<Film> films;

    public Actor(String name) {
        this.name = name;
        this.films = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    @Override
    public String toString() {
        return "Actor{name='" + name + "', films=" + films.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Actor actor = (Actor) o;
        return Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

// Клас Фільм
class Film {
    private String title;
    private int year;
    private Set<Actor> actors;

    public Film(String title, int year) {
        this.title = title;
        this.year = year;
        this.actors = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
        actor.addFilm(this);
    }

    @Override
    public String toString() {
        return "Film{title='" + title + "', year=" + year + ", actors=" + actors.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Film film = (Film) o;
        return year == film.year && Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year);
    }
}

// Клас База даних про кіно
class MovieDatabase {
    private Set<Film> films;
    private Set<Actor> actors;

    // Map для швидкого пошуку акторів по імені
    private Map<String, Actor> actorsByName;

    public MovieDatabase() {
        this.films = new HashSet<>();
        this.actors = new HashSet<>();
        this.actorsByName = new HashMap<>();
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public void addActor(Actor actor) {
        actors.add(actor);
        actorsByName.put(actor.getName(), actor);
    }

    public Set<Film> getFilms() {
        return films;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    // Задача 1: Визначити, чи є актор, який не зіграв в жодному фільмі
    // Використовуємо нетипізований ітератор
    public boolean hasActorWithoutFilms() {
        Iterator it = actors.iterator();
        while (it.hasNext()) {
            Actor actor = (Actor) it.next();
            if (actor.getFilms().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    // Задача 2: Скласти список акторів, з якими коли-небудь в одному фільмі грав
    // заданий актор
    // Використовуємо типізований цикл for-each
    public Set<Actor> getCoActors(Actor targetActor) {
        Set<Actor> coActors = new HashSet<>();

        for (Film film : targetActor.getFilms()) {
            for (Actor actor : film.getActors()) {
                if (!actor.equals(targetActor)) {
                    coActors.add(actor);
                }
            }
        }

        return coActors;
    }

    // Задача 3: Знайти фільм з найбільшою кількістю акторів
    // Використовуємо типізований ітератор
    public Film getFilmWithMostActors() {
        if (films.isEmpty()) {
            return null;
        }

        Film maxFilm = null;
        int maxActors = -1;

        Iterator<Film> iterator = films.iterator();
        while (iterator.hasNext()) {
            Film film = iterator.next();
            if (film.getActors().size() > maxActors) {
                maxActors = film.getActors().size();
                maxFilm = film;
            }
        }

        return maxFilm;
    }

    // 1. Отримати кількість фільмів по рокам
    public Map<Integer, Integer> getFilmCountByYear() {
        Map<Integer, Integer> countByYear = new HashMap<>();

        for (Film film : films) {
            int year = film.getYear();
            countByYear.put(year, countByYear.getOrDefault(year, 0) + 1);
        }

        return countByYear;
    }

    // 2. Отримати кількість фільмів для кожного актора
    public Map<Actor, Integer> getActorFilmCount() {
        Map<Actor, Integer> filmCount = new HashMap<>();

        for (Actor actor : actors) {
            filmCount.put(actor, actor.getFilms().size());
        }

        return filmCount;
    }

    // 3. Знайти актора по імені (використання Map для швидкого пошуку)
    public Actor findActorByName(String name) {
        return actorsByName.get(name);
    }

    // 4. Отримати топ N акторів по кількості фільмів
    public Set<Actor> getTopActorsByFilmCount(int n) {
        // Створюємо список пар (актор, кількість фільмів)
        List<Map.Entry<Actor, Integer>> actorList = new ArrayList<>();

        for (Actor actor : actors) {
            actorList.add(new AbstractMap.SimpleEntry<>(actor, actor.getFilms().size()));
        }

        // Сортуємо за кількістю фільмів (спадання)
        actorList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Беремо перші N
        Set<Actor> topActors = new LinkedHashSet<>();
        for (int i = 0; i < Math.min(n, actorList.size()); i++) {
            topActors.add(actorList.get(i).getKey());
        }

        return topActors;
    }

    // 5. Отримати Map фільмів по акторам
    public Map<Actor, Set<Film>> getFilmsByActor() {
        Map<Actor, Set<Film>> filmsByActor = new HashMap<>();

        for (Actor actor : actors) {
            filmsByActor.put(actor, new HashSet<>(actor.getFilms()));
        }

        return filmsByActor;
    }
}