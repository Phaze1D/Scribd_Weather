package com.steadypathapp.david.scribdweather;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by david on 11/8/16.
 */


public class YahooNetworkTask extends AsyncTask<String, Object, Wrapper> {

    private YahooNetworkTaskListener taskListener;

    public YahooNetworkTask(YahooNetworkTaskListener listener){
        this.taskListener = listener;
    }

    @Override
    protected Wrapper doInBackground(String... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://query.yahooapis.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        YahooAPI wService = retrofit.create(YahooAPI.class);
        String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\""+ params[0] + "\")";
        Call<String> weather = wService.getWeather(query);

        try {

            return new Wrapper(weather.execute(), params[0]);
        } catch (IOException e) {
            return null;
        }
    }


    @Override
    protected void onPostExecute(Wrapper result) {
        if(this.taskListener != null){
            taskListener.onFinished(result.city, result.result);
        }
    }

    public interface YahooNetworkTaskListener{
        void onFinished(String city, Response<String> res);
    }

}

class Wrapper{
    public Response<String> result;
    public String city;


    public Wrapper(Response<String> result, String city){
        this.result = result;
        this.city = city;

    }
}

