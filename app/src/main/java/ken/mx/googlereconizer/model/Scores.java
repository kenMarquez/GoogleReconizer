package ken.mx.googlereconizer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ken on 30/10/16.
 */

public class Scores {


    @SerializedName("anger")
    @Expose
    private double anger;
    @SerializedName("contempt")
    @Expose
    private double contempt;
    @SerializedName("disgust")
    @Expose
    private double disgust;
    @SerializedName("fear")
    @Expose
    private double fear;
    @SerializedName("happiness")
    @Expose
    private double happiness;
    @SerializedName("neutral")
    @Expose
    private double neutral;
    @SerializedName("sadness")
    @Expose
    private double sadness;
    @SerializedName("surprise")
    @Expose
    private double surprise;

    /**
     * @return The anger
     */
    public double getAnger() {
        return anger;
    }

    /**
     * @param anger The anger
     */
    public void setAnger(double anger) {
        this.anger = anger;
    }

    /**
     * @return The contempt
     */
    public double getContempt() {
        return contempt;
    }

    /**
     * @param contempt The contempt
     */
    public void setContempt(double contempt) {
        this.contempt = contempt;
    }

    /**
     * @return The disgust
     */
    public double getDisgust() {
        return disgust;
    }

    /**
     * @param disgust The disgust
     */
    public void setDisgust(double disgust) {
        this.disgust = disgust;
    }

    /**
     * @return The fear
     */
    public double getFear() {
        return fear;
    }

    /**
     * @param fear The fear
     */
    public void setFear(double fear) {
        this.fear = fear;
    }

    /**
     * @return The happiness
     */
    public double getHappiness() {
        return happiness;
    }

    /**
     * @param happiness The happiness
     */
    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

    /**
     * @return The neutral
     */
    public double getNeutral() {
        return neutral;
    }

    /**
     * @param neutral The neutral
     */
    public void setNeutral(double neutral) {
        this.neutral = neutral;
    }

    /**
     * @return The sadness
     */
    public double getSadness() {
        return sadness;
    }

    /**
     * @param sadness The sadness
     */
    public void setSadness(double sadness) {
        this.sadness = sadness;
    }

    /**
     * @return The surprise
     */
    public double getSurprise() {
        return surprise;
    }

    /**
     * @param surprise The surprise
     */
    public void setSurprise(double surprise) {
        this.surprise = surprise;
    }

}
