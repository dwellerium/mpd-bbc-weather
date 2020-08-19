package gcu.mpd.mpd_weather;

//Dean Robertson
//S1826626

public class Day {
    private String title;
    private String maxTemp;
    private String minTemp;
    private String winDirection;
    private String winSpeed;

    public Day(String title, String description) {
        this.title = title;
        parseDescription(description);
    }

    public String getTitle() {
        return title;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getWinDirection() {
        return winDirection;
    }

    public String getWinSpeed() {
        return winSpeed;
    }

    private void parseDescription(String description) {
        String[] info = description.split(",");
        for (int i = 0; i < info.length; i++) {
            String[] values = info[i].split(":");
            String detail = values[0].replace(" ", "");
            String value = values[1];

            switch (detail) {
                case "WindDirection":
                    this.winDirection = value;
                    break;
                case "MaximumTemperature":
                    this.maxTemp = value;
                    break;
                case "MinimumTemperature":
                    this.minTemp = value;
                    break;
                case "WindSpeed":
                    this.winSpeed = value;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Day{" +
                "title='" + title + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                ", minTemp='" + minTemp + '\'' +
                ", winDirection='" + winDirection + '\'' +
                ", winSpeed='" + winSpeed + '\'' +
                '}';
    }
}
