package com.steadypathapp.david.scribdweather;


import org.json.JSONObject;


/**
 * Created by david on 11/8/16.
 */

public class YahooAPI {

    private static YahooAPI instance;
    private static String YAHOO_URL = "https://query.yahooapis.com/";

    public static YahooAPI getInstance()
    {
        if (instance == null)
        {
            instance = new YahooAPI();
        }

        return instance;
    }

    public static JSONObject query(String city){
        String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + city + "\")";

        return new JSONObject();
    }
}
