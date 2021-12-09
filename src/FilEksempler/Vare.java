package FilEksempler;
import java.io.Serializable;

public class Vare implements Serializable {

    private int antal;
    private String navn;
    private double pris;

    //Default
    public Vare() {
    }

    //Constructor
    public Vare(int antal, String navn, double pris) {
        this.antal = antal;
        this.navn = navn;
        this.pris = pris;
    }

    //Getters & Setters
    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }
}
