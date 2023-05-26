package com.example.vreme;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;

public class WeatherController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField userInput;

    @FXML
    private Label temp;

    @FXML
    private Label feelslike;

    @FXML
    private Label airHumidity;

    @FXML
    private Label windIntensity;

    @FXML
    private Label localTime;

@FXML
    private Label cityName;

    @FXML
    private Label countryName;

    public String getUserInputString() {
        String userInputString = userInput.getText();
        return userInputString;
    }

    @FXML
    public void onHelloButtonClick() throws ParseException {
        APIController apiController = APIController.getInstance(); //SINGLETON
        WeatherResult weatherResult = WeatherResult.getInstance(); //SINGLETON
        String userInputString = userInput.getText();
        apiController.getWeatherData(userInputString);
        if(apiController.getStatusCode()!=200) return;
        weatherResult.getDataFromJSON();
        updateLabels();
        System.out.println(getUserInputString());
    }

    public void updateLabels() {
        WeatherResult weatherResult = WeatherResult.getInstance();
        temp.setText(weatherResult.getTemp() + "°C");
        feelslike.setText(weatherResult.getFeelslike() + "°C");
        airHumidity.setText(weatherResult.getAirHumidity() + "%");
        windIntensity.setText(weatherResult.getWindIntensity() + "km/h");
        localTime.setText(weatherResult.getLocalTime());
        cityName.setText(weatherResult.getCityName() + ", ");
        countryName.setText(weatherResult.getCountryName());
    }



}