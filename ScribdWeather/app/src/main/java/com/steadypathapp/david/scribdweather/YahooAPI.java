package com.steadypathapp.david.scribdweather;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by david on 11/8/16.
 */

public interface YahooAPI {

    public static String URL = "https://query.yahooapis.com";

    @GET("v1/public/yql")
    Call<String> getWeather(@Query("q") String query);

}
