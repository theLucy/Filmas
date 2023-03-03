public class Filmas {
    private String pavadinimas;
    private float ivertinimas;
    private String zanras;

    public Filmas(String pavadinimas, float ivertinimas, String zanras) {
        this.pavadinimas = pavadinimas;
        this.ivertinimas = ivertinimas;
        this.zanras = zanras;
    }

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

    public String getNameRatingString() {
        return pavadinimas + " : " + ivertinimas + "*";
    }

    @Override
    public String toString() {
        return "Filmas{" +
                "pavadinimas='" + pavadinimas + '\'' +
                ", ivertinimas=" + ivertinimas +
                ", zanras='" + zanras + '\'' +
                '}';
    }
}
