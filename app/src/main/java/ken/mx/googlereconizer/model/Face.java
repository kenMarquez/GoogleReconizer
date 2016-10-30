package ken.mx.googlereconizer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ken on 30/10/16.
 */
public class Face {

    private String score;

    private String url;

    public Face(String score, String url) {
        this.score = score;
        this.url = url;
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
}
