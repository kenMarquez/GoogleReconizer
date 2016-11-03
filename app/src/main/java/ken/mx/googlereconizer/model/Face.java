package ken.mx.googlereconizer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ken on 30/10/16.
 */
public class Face {

    private String score;

    private String url;
    private String id;
    private int potencial;

    public Face ()
    {

    }

    public Face(String score, String url) {
        this.score = score;
        this.url = url;
    }

    public Face(String score, String url, String id) {
        this.score = score;
        this.url = url;
        this.id = id;
    }

    public Face(String score, String url, String id, int potencial) {
        this.score = score;
        this.url = url;
        this.id = id;
        this.potencial = potencial;
    }

    public int getPotencial() {
        return potencial;
    }

    public void setPotencial(int potencial) {
        this.potencial = potencial;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Face{" +
                "score='" + score + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
