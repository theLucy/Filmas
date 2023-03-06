import java.util.Comparator;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;

public class Filmas {
    private String pavadinimas;
    private float ivertinimas;
    private String zanras;
    private int trukme;

    /* PREDICATES */
    public static final Predicate<Filmas> defaultFilter = x -> true;
    public static final Predicate<Filmas> isShortFilm = x -> x.trukme < 60;
    public static final Predicate<Filmas> hasHighRating = x -> x.ivertinimas >= 9.0;
    public static final Predicate<Filmas> isDrama = x -> x.zanras.equalsIgnoreCase("drama");

    /* COMPARATORS */
    public static final Comparator<Filmas> byRating = Comparator.comparingDouble(Filmas::getIvertinimas);
    public static final Comparator<Filmas> byRatingDesc = Comparator.comparingDouble(Filmas::getIvertinimas).reversed();
    public static final Comparator<Filmas> byLength = Comparator.comparingInt(Filmas::getTrukme);
    public static final Comparator<Filmas> byLengthDesc = Comparator.comparingInt(Filmas::getTrukme).reversed();
    public static final Comparator<Filmas> byName = Comparator.comparing(Filmas::getPavadinimas);
    public static final Comparator<Filmas> byNameDesc = Comparator.comparing(Filmas::getPavadinimas).reversed();

    public Filmas(String pavadinimas, float ivertinimas, String zanras, int trukme) {
        this.pavadinimas = pavadinimas;
        this.ivertinimas = ivertinimas;
        this.zanras = zanras;
        this.trukme = trukme;
    }


    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public float getIvertinimas() {
        return ivertinimas;
    }

    public void setIvertinimas(float ivertinimas) {
        this.ivertinimas = ivertinimas;
    }

    public String getZanras() {
        return zanras;
    }

    public void setZanras(String zanras) {
        this.zanras = zanras;
    }

    public int getTrukme() {
        return trukme;
    }

    public void setTrukme(int trukme) {
        this.trukme = trukme;
    }

    // </editor-fold>

    public String getNameRatingString() {
        return pavadinimas + " : " + ivertinimas + "*";
    }

    @Override
    public String toString() {
        return "Filmas{" +
                "pavadinimas='" + pavadinimas + '\'' +
                ", ivertinimas=" + ivertinimas +
                ", zanras='" + zanras + '\'' +
                ", trukme=" + trukme +
                '}';
    }
}
