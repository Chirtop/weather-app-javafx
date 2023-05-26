package com.example.vreme;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class APIController {
    private static APIController instance;
    private String apiKey;
    private String weatherURL;
    private String APIResponse;

    private int statusCode;
    public APIController() {
        this.apiKey = "";//your weatherapi.com key here
        this.weatherURL = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=";
    }

    public String getAPIResponse() {
        return APIResponse;
    }
    public int getStatusCode() {
        return statusCode;
    }

    //SINGLETON
    public static APIController getInstance() {
        if (instance == null) {
            instance = new APIController();
        }
        return instance;
    }

    public void getWeatherData(String cityName) {

        if(cityName.equals(null) || cityName.equals("")) {
            System.out.println("City name is null");
            return;
        }

        if (cityName.contains(" ")) {
            cityName = getCityNameWithMultipleWords(cityName);
        }

        String fullWeatherURL = weatherURL + cityName;

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet weatherRequest = new HttpGet(fullWeatherURL);
        HttpResponse weatherResponse = null;

        try {
            weatherResponse = httpClient.execute(weatherRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        statusCode = weatherResponse.getStatusLine().getStatusCode();
        if(statusCode != 200) {
            System.out.println("Error getting weather data");
            return;
        }

        HttpEntity weatherEntity = weatherResponse.getEntity();
        String weatherResponseBody = null;
        try {
            weatherResponseBody = EntityUtils.toString(weatherEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(weatherResponseBody);
        APIResponse = weatherResponseBody;
    }


    public String getCityNameWithMultipleWords(String cityName){
        String[] cityNameArray = cityName.split(" ");
        String cityNameWithDash = "";
        for(int i = 0; i < cityNameArray.length; i++){
            cityNameWithDash += cityNameArray[i] + "-";
        }
        cityNameWithDash = cityNameWithDash.substring(0, cityNameWithDash.length() - 1);
        return cityNameWithDash;
    }



}
