package ken.mx.googlereconizer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ken on 30/10/16.
 */

public class ResponseReconizing {

    @SerializedName("faceRectangle")
    @Expose
    private FaceRectangle faceRectangle;
    @SerializedName("scores")
    @Expose
    private Scores scores;

    /**
     *
     * @return
     * The faceRectangle
     */
    public FaceRectangle getFaceRectangle() {
        return faceRectangle;
    }

    /**
     *
     * @param faceRectangle
     * The faceRectangle
     */
    public void setFaceRectangle(FaceRectangle faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    /**
     *
     * @return
     * The scores
     */
    public Scores getScores() {
        return scores;
    }

    /**
     *
     * @param scores
     * The scores
     */
    public void setScores(Scores scores) {
        this.scores = scores;
    }
}
