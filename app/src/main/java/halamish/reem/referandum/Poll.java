package halamish.reem.referandum;

/**
 * Created by Re'em on 5/19/2016.
 */
public class Poll {
    private String title;
    private String description;
    private String[] choices;
    private long endTimeMs;

    public Poll(String title, String description, String[] choices, long endTimeMs) {
        this.title = title;
        this.description = description;
        this.choices = choices;
        this.endTimeMs = endTimeMs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public long getEndTimeMs() {
        return endTimeMs;
    }

    public void setEndTimeMs(long endTimeMs) {
        this.endTimeMs = endTimeMs;
    }
}
