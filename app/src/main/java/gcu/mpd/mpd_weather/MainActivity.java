package gcu.mpd.mpd_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<Day> days;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list);
        new FetchWeather("2643123").execute((Void) null);
    }

    class FetchWeather extends AsyncTask<Void, Void, Boolean> {
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
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                adapter= new CustomAdapter((ArrayList<Day>) days, getApplicationContext());
                listView.setAdapter(adapter);
            }
        }
    }
}
