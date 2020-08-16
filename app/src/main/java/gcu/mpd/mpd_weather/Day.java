package gcu.mpd.mpd_weather;

public class Day {
    public String title;
    public String description;
    public String maxTemp;
    public String minTemp;
    public String winDirection;
    public String winSpeed;
    public String visibility;

    public Day(String title, String description) {
        this.title = title;
        parseDescription(description);
    }

    private void  parseDescription(String description) {
        this.description = description;
    }
}
