package serve.serveup.dataholder;

import com.squareup.moshi.Json;
import java.io.Serializable;

import serve.serveup.utils.Utils;

public class MealInfo implements Serializable {

    @Json(name = "ime_jedi")
    private String imeJedi;
    @Json(name = "opis_jedi")
    private String opisJedi;
    @Json(name = "cena")
    private float cena;
    @Json(name = "kolicina")
    private int kolicina;
    private String uniqueName;

    public String getImeJedi() {
        return imeJedi;
    }
    public void setImeJedi(String imeJedi) {
        this.imeJedi = imeJedi;
    }
    public String getOpisJedi() {
        return opisJedi;
    }
    public void setOpisJedi(String opisJedi) {
        this.opisJedi = opisJedi;
    }
    public float getCena() {
        return cena;
    }
    public void setCena(float cena) {
        this.cena = cena;
    }
    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { this.kolicina = kolicina; }

    public void setUniqueName() {
        this.uniqueName = "MEAL:" + Utils.randomID();
    }

    public String getUniqueName() {
        return this.uniqueName;
    }

    @Override
    public String toString() {
        return "Ime: " + this.getImeJedi() +
                ", opis: " + this.getOpisJedi() +
                ", cena: " + this.getCena() +
                ", kolicina: " + this.getKolicina() +
                ", unique name: " + this.getUniqueName();
    }
}