import java.util.*;

public class filmsAndActors {
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
        Film film1 = new Film("Титанік");
        film1.addActor(actor1);
        film1.addActor(actor2);

        Film film2 = new Film("Початок");
        film2.addActor(actor1);
        film2.addActor(actor3);

        Film film3 = new Film("Месники");
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
        List<Actor> coActors = database.getCoActors(actor1);
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
    }
}

// Клас Актор
class Actor {
    private String name;
    private List<Film> films;

    public Actor(String name) {
        this.name = name;
        this.films = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film) {
        if (!films.contains(film)) {
            films.add(film);
        }
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
    private List<Actor> actors;

    public Film(String title) {
        this.title = title;
        this.actors = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void addActor(Actor actor) {
        if (!actors.contains(actor)) {
            actors.add(actor);
            actor.addFilm(this);
        }
    }

    @Override
    public String toString() {
        return "Film{title='" + title + "', actors=" + actors.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

// Клас База даних про кіно
class MovieDatabase {
    private List<Film> films;
    private List<Actor> actors;

    public MovieDatabase() {
        this.films = new ArrayList<>();
        this.actors = new ArrayList<>();
    }

    public void addFilm(Film film) {
        if (!films.contains(film)) {
            films.add(film);
        }
    }

    public void addActor(Actor actor) {
        if (!actors.contains(actor)) {
            actors.add(actor);
        }
    }

    public List<Film> getFilms() {
        return films;
    }

    public List<Actor> getActors() {
        return actors;
    }

    // Задача 1: Визначити, чи є актор, який не зіграв в жодному фільмі
    // Використовуємо нетипізований ітератор (варіант 8 - задача 1 = a)
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
    // Використовуємо типізований цикл for-each (варіант 8 - задача 2 = c)
    public List<Actor> getCoActors(Actor targetActor) {
        List<Actor> coActors = new ArrayList<>();

        for (Film film : targetActor.getFilms()) {
            for (Actor actor : film.getActors()) {
                if (!actor.equals(targetActor) && !coActors.contains(actor)) {
                    coActors.add(actor);
                }
            }
        }

        return coActors;
    }

    // Задача 3: Знайти фільм з найбільшою кількістю акторів
    // Використовуємо типізований ітератор (варіант 8 - задача 3 = b)
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
}