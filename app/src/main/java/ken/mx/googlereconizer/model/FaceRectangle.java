package ken.mx.googlereconizer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ken on 30/10/16.
 */

public class FaceRectangle {


    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("left")
    @Expose
    private int left;
    @SerializedName("top")
    @Expose
    private int top;
    @SerializedName("width")
    @Expose
    private int width;

    /**
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return The left
     */
    public int getLeft() {
        return left;
    }

    /**
     * @param left The left
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * @return The top
     */
    public int getTop() {
        return top;
    }

    /**
     * @param top The top
     */
    public void setTop(int top) {
        this.top = top;
    }

    /**
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    public void setWidth(int width) {
        this.width = width;
    }
}
