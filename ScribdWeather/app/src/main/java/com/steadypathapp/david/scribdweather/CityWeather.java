package com.steadypathapp.david.scribdweather;

/**
 * Created by david on 11/8/16.
 */

public class CityWeather {

    private String date;
    private String day;
    private String high;
    private String low;
    private String text;

    public CityWeather(String date, String day, String high, String low, String text){
        this.date=date;
        this.day=day;
        this.high=high;
        this.low=low;
        this.text=text;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getText() {
        return text;
    }
}
