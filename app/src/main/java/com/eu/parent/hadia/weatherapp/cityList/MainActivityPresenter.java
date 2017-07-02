package com.eu.parent.hadia.weatherapp.cityList;

/**
 * Created by hadia on 7/2/17.
 */

public interface MainActivityPresenter {
    public void requetWeatherData(final String placeID, final double latitude, final double longitude);
}
