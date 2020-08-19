package gcu.mpd.mpd_weather;

//Dean Robertson
//S1826626

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WeatherParser {

    private static final String TAG = "parser";
    private InputStream feed;
    private String title = null;
    private String description = null;
//    private String maxTemp = null;
//    private String minTemp = null;
//    private String winDirection = null;
//    private String winSpeed = null;
//    private String visibility = null;
    private boolean isItem = false;
    List<Day> days = new ArrayList<>();

    public WeatherParser(InputStream feed) {
        this.feed = feed;
    }

    public List<Day> parseFeed() throws IOException {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(feed, null);

            xpp.nextTag();
            while (xpp.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xpp.getEventType();

                String name = xpp.getName();
//                System.out.println(name);
//                Log.d(TAG, name);

                if(name == null || name.equalsIgnoreCase("channel"))
                    continue;

                if(eventType == XmlPullParser.END_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if(eventType == XmlPullParser.START_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        System.out.println("NEW ITEM");
                        isItem = true;
                        title = null;
                        description = null;
                        continue;
                    }
                }

                String result = "";
                if(xpp.next() == XmlPullParser.TEXT) {
                    result = xpp.getText();
//                    System.out.println(result);
                    xpp.nextTag();
                }
                xpp.next();

                if (!isItem) {
                    continue;
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if(name.equalsIgnoreCase(("description"))) {
                    description = result;
                }

                // && maxTemp != null && minTemp != null && winDirection != null && winSpeed != null && visibility != null

                if(title != null && description != null) {
                    Day day = new Day(title, description);
                    days.add(day);
                    title = null;
                    description = null;
                }
            }
        }
        catch (XmlPullParserException e) {
            Log.e("Parser", "Failed to parse day", e);
        }
        finally {
            feed.close();
            Log.d("STREAM", "Closed stream");
        }
        return days;
    }
}
