package serve.serveup.dataholder;

import android.graphics.Bitmap;

public class RestaurantHome {
    private int id;
    private String name;
    private String type;
    private float rating;
    private Bitmap image;

    // Constructor
    public RestaurantHome(int id, String name, String type, float rating, Bitmap image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rating = rating;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }
}
