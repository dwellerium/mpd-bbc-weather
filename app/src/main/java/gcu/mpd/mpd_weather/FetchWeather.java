package gcu.mpd.mpd_weather;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FetchWeather extends AsyncTask<Void, Void, Boolean> {

    public List<Day> days;
    private final String endpoint = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    private URL url;

    public FetchWeather(String weatherID) {

        try {
            url = new URL(endpoint + weatherID);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            InputStream inputStream = url.openConnection().getInputStream();
            days = new WeatherParser(inputStream).parseFeed();
            System.out.println(days.size());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean success) {

        if (success) {

        }
    }
}
