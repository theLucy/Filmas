import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        List<Filmas> filmai = List.of(
                new Filmas("The Shawshank Redemption",9.2f,"Drama"),
                new Filmas("The Godfather",9.2f,"Crime"),
                new Filmas("The Dark Knight",9.0f,"Action"),
                new Filmas("The Godfather Part II",9.0f,"Crime"),
                new Filmas("12 Angry Men",9.0f,"Drama"),
                new Filmas("Schindlers List",8.9f,"Drama")
        );

        var filmaiPagalZanra = filmai.stream()
                .collect(Collectors.groupingBy(
                        Filmas::getZanras,
                        Collectors.mapping(Filmas::getNameRatingString,
                                Collectors.toSet())
                ));

        System.out.println(filmaiPagalZanra);
    }
}