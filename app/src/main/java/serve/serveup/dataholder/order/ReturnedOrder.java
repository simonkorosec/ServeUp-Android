package serve.serveup.dataholder.order;

import com.squareup.moshi.Json;

import java.util.List;

import serve.serveup.dataholder.MealInfo;

public class ReturnedOrder {

    @Json(name = "id_narocila")
    private int idNarocila;
    @Json(name = "cas_prevzema")
    private String casPrevzema;
    @Json(name = "cas_narocila")
    private String casNarocila;
    @Json(name = "ime_restavracije")
    private String imeRestavracije;
    @Json(name = "cena")
    private double cena;
    @Json(name = "status")
    private int status;
    @Json(name = "jedi")
    private List<MealInfo> meals;


    public void setCasNarocila(String casNarocila) {
        this.casNarocila = casNarocila;
    }
    public void setCasPrevzema(String casPrevzema) {
        this.casPrevzema = casPrevzema;
    }
    public void setMeals(List<MealInfo> meals) {
        this.meals = meals;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setIdNarocila(int idNarocila) {
        this.idNarocila = idNarocila;
    }

    public void setImeRestavracije(String imeRestavracije) {
        this.imeRestavracije = imeRestavracije;
    }

    public String getCasNarocila() {
        return casNarocila;
    }
    public String getCasPrevzema() {
        return casPrevzema;
    }

    public int getStatus() {
        return status;
    }

    public double getCena() {
        return cena;
    }

    public List<MealInfo> getMeals() {
        return meals;
    }

    public int getIdNarocila() {
        return idNarocila;
    }

    public String getImeRestavracije() {
        return imeRestavracije;
    }
}
