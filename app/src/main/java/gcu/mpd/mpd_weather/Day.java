package gcu.mpd.mpd_weather;

public class Day {
    public String title;
    public String maxTemp;
    public String minTemp;
    public String winDirection;
    public String winSpeed;
    public String visibility;

    public Day(String title, String maxTemp, String minTemp, String winDirection, String winSpeed, String visibility) {
        this.title = title;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.winDirection = winDirection;
        this.winSpeed = winSpeed;
        this.visibility = visibility;
    }


}
