import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class App {

    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        Consumer<String> printToSout = System.out::println;
        Consumer<String> printToFile = App::printToFile;
        Consumer<String> printToSoutAndFile = x -> {
            System.out.println(x);
            printToFile(x);
        };

        List<Filmas> filmai = List.of(
                new Filmas("The Shawshank Redemption", 9.2f, "Drama", 90),
                new Filmas("The Godfather", 9.2f, "Crime", 80),
                new Filmas("The Dark Knight", 9.0f, "Action", 100),
                new Filmas("The Godfather Part II", 9.0f, "Crime", 20),
                new Filmas("12 Angry Men", 9.0f, "Drama", 30),
                new Filmas("Schindlers List", 8.9f, "Drama", 59),
                new Filmas("Naujas filmas", 8.9f, "Comedy", 200)
        );

        var filmaiString = findFilm(filmai);

        if(filmaiString.isPresent()) {
            System.out.print("""
                Kur norite isvesti rezultata:
                1 > i ekrana
                2 > i faila
                3 > i ekrana ir i faila
                """);
            var isvedimas = switch (in.nextLine()) {
                case "2" -> printToFile;
                case "3" -> printToSoutAndFile;
                default -> printToSout;
            };
           isvedimas.accept(filmaiString.get());
        }

    }

    private static void printToFile(String x) {
        try {
            Files.writeString(new File("filmas.txt").toPath(),
                    x, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("Ivyko failo rasymo klaida!");
        }
    }

    public static Optional<String> findFilm(List<Filmas> filmai) {
        System.out.print("""
                Pagal ka norite filmus filtruoti:
                1 > trumpus filmus
                2 > geriausiai ivertintus
                3 > dramas
                4 > pagal zodi pavadinime
                """);
        var filtras = switch (in.nextLine()) {
            case "1" -> Filmas.isShortFilm;
            case "2" -> Filmas.hasHighRating;
            case "3" -> Filmas.isDrama;
            case "4" -> {
                String zodis = in.next().toLowerCase();
                in.nextLine();
                yield (Predicate<Filmas>) o -> o.getPavadinimas().toLowerCase().contains(zodis);
            }
            default -> Filmas.defaultFilter;
        };

        System.out.print("""
                Pagal ka norite filmus surusiuoti:
                1 > pagal pavadinima
                2 > pagal ivertinima
                3 > pagal trukme
                4 > pagal pavadinima[atvirksciai]
                5 > pagal ivertinima[atvirksciai]
                6 > pagal trukme[atvirksciai]
                """);
        var rusiavimas = switch (in.nextLine()) {
            case "2" -> Filmas.byRating;
            case "3" -> Filmas.byLength;
            case "4" -> Filmas.byNameDesc;
            case "5" -> Filmas.byRatingDesc;
            case "6" -> Filmas.byLengthDesc;
            default -> Filmas.byName;
        };

        var filmaiString = filmai.stream()
                .filter(filtras)
                .sorted(rusiavimas)
                .map(filmas -> filmas.toString().concat(System.lineSeparator()))
                .reduce("", String::concat);

        return filmaiString.length() == 0 ? Optional.empty() : Optional.of(filmaiString);
    }
}
