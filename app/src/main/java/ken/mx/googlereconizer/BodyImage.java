package ken.mx.googlereconizer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ken on 29/10/16.
 */

public class BodyImage {

    @SerializedName("url")
    @Expose
    String url;

    public BodyImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
