package com.eu.parent.hadia.weatherapp.cityList;

import android.os.Bundle;
import android.widget.Toast;

import com.eu.parent.hadia.weatherapp.Database.GetAllCitiesData;
import com.eu.parent.hadia.weatherapp.Database.SaveCityDataInteractor;
import com.eu.parent.hadia.weatherapp.Database.SaveWeatherDataInteractor;
import com.eu.parent.hadia.weatherapp.GetWeatherResponse.CityResponse;
import com.eu.parent.hadia.weatherapp.common.Interactor;
import com.eu.parent.hadia.weatherapp.common.InteractorSuccessListener;
import com.eu.parent.hadia.weatherapp.model.CityModel;
import com.eu.parent.hadia.weatherapp.model.WeatherItemModel;

import java.util.ArrayList;
import java.util.List;

import nucleus.presenter.Presenter;

/**
 * Created by Hadia .
 * IBM
 *
 * @author Hadia
 *         on 6/12/17.
 */

public class MainActivityPresenterImp extends Presenter<MainActivity> implements MainActivityPresenter {

    private ArrayList<CityModel> allData;
    public MainActivityPresenterImp() {
allData= new ArrayList<>();

    }
    @Override
    public void create(Bundle bundle) {
        super.create(bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onTakeView(MainActivity mainActivity) {
        super.onTakeView(mainActivity);
      //  if(getView()!=null)
        //    getView().bindLocations(cache.getGooglePlaces(),cache.getSquarePlaces());

    }

    @Override
    public void requetWeatherData(final String placeID, final double latitude, final double longitude) {


          new  GetAllCitiesData().setSuccessListener(new InteractorSuccessListener<List<CityModel>>() {
              @Override
              public void onSuccess(Interactor interactor, List<CityModel> result) {
                  if(result!=null&&result.size()>0)
                  {
                      allData= new ArrayList<CityModel>();
                      for (CityModel cityitem:
                           result) {
                          getCityData("",cityitem.getLat(),cityitem.getLon());
                          Toast.makeText(getView(), "Update Data", Toast.LENGTH_LONG).show();
                      }
                  }
                  else
                  {
                      if(placeID!=null&&!placeID.isEmpty())
                      { getCityData(placeID,0,0);

                      }
                      else{
                          getCityData("",latitude,longitude);
                      }

                  }
              }
          }).execute();
        }






private void getCityData(final String placeID, final double latitude, final double longitude)
{
    new GetCityWeatherOnlineInteractor(placeID,latitude, longitude).setSuccessListener(new InteractorSuccessListener<CityResponse>() {
        @Override
        public void onSuccess(Interactor interactor, CityResponse result) {


                CityModel cityData= new CityModel();
                cityData.fill(result);
                new SaveCityDataInteractor(cityData).execute();
                for (int i=0;i< result.getList().size() ;i++) {
                    WeatherItemModel weatherItem = new WeatherItemModel(cityData).fill(result.getList().get(i));
                    weatherItem.setmId(cityData.getId()+"_"+result.getList().get(i).getDt());
                    new SaveWeatherDataInteractor(weatherItem).execute();
                    cityData.getmWeatherModel().add(weatherItem);
                }
//                    cityData.setName(result.get);
//                    getView().bind();
                allData.add(cityData);

                new GetAllCitiesData().execute();
                if (getView() != null) {
                    getView().bindLocations(allData);
                    Toast.makeText(getView(), "Nearby Restaurants", Toast.LENGTH_LONG).show();
                }




        }
    }).execute();
}

}
