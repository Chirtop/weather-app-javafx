package com.example.vreme;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherResult {
    private String temp;
    private String feelslike;
    private String airHumidity;
    private String windIntensity;
    private String localTime;
    private String cityName;
    private String countryName;

    private static WeatherResult instance;

private WeatherResult() {
        this.temp = "";
        this.feelslike = "";
        this.airHumidity = "";
        this.windIntensity = "";
        this.localTime = "";
        this.cityName = "";
        this.countryName = "";
    }

    public static WeatherResult getInstance() {
        if (instance == null) {
            instance = new WeatherResult();
        }
        return instance;
    }
    public String getTemp() {
        return temp;
    }

    public String getFeelslike() {
        return feelslike;
    }

    public String getAirHumidity() {
        return airHumidity;
    }

    public String getWindIntensity() {
        return windIntensity;
    }

    public String getLocalTime() {
        return localTime;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void getDataFromJSON() throws ParseException {
        APIController apiController = APIController.getInstance();
        String jsonObjectWeather = apiController.getAPIResponse();

        JSONParser JSONParser = new JSONParser();
        JSONObject weatherInfo = (JSONObject) JSONParser.parse(apiController.getAPIResponse());
        JSONObject locationInfo = (JSONObject) weatherInfo.get("location");
        cityName = (String) locationInfo.get("name");
        countryName = (String) locationInfo.get("country");
        localTime = (String) locationInfo.get("localtime");

        JSONObject currentWeatherInfo = (JSONObject) weatherInfo.get("current");
        temp = currentWeatherInfo.get("temp_c").toString();
        feelslike = currentWeatherInfo.get("feelslike_c").toString();
        airHumidity = currentWeatherInfo.get("humidity").toString();
        windIntensity = currentWeatherInfo.get("wind_kph").toString();


    }

}
