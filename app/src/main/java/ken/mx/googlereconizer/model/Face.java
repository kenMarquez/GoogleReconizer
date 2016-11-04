package ken.mx.googlereconizer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ken on 30/10/16.
 */
public class Face {
    private int id;
    private int potencial;
    private String url;
    private double happiness;
    private double sadness;
    private double anger;
    private double disgust;
    private double neutral;

    public Face(){}

    public Face(int id, int potencial, String url, double happiness, double sadness, double anger, double disgust, double neutral) {
        this.id = id;
        this.potencial = potencial;
        this.url = url;
        this.happiness = happiness;
        this.sadness = sadness;
        this.anger = anger;
        this.disgust = disgust;
        this.neutral = neutral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPotencial() {
        return potencial;
    }

    public void setPotencial(int potencial) {
        this.potencial = potencial;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

    public double getSadness() {
        return sadness;
    }

    public void setSadness(double sadness) {
        this.sadness = sadness;
    }

    public double getAnger() {
        return anger;
    }

    public void setAnger(double anger) {
        this.anger = anger;
    }

    public double getDisgust() {
        return disgust;
    }

    public void setDisgust(double disgust) {
        this.disgust = disgust;
    }

    public double getNeutral() {
        return neutral;
    }

    public void setNeutral(double neutral) {
        this.neutral = neutral;
    }

    @Override
    public String toString() {
        return "Face{" +
                "id=" + id +
                ", potencial=" + potencial +
                ", url='" + url + '\'' +
                ", happiness=" + happiness +
                ", sadness=" + sadness +
                ", anger=" + anger +
                ", disgust=" + disgust +
                ", neutral=" + neutral +
                '}';
    }
}
