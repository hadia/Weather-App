package com.eu.parent.hadia.weatherapp.cityList.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.eu.parent.hadia.weatherapp.R;
import com.eu.parent.hadia.weatherapp.model.CityModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityListHolder> implements CompoundButton.OnCheckedChangeListener{

    private List<CityModel> locationObjects;

    protected Context context;

//    private List<ViewEntityObject> allRadioButton;

    //private DatabaseQuery query;

    int[] idList = new int[]{R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4};

   // private CustomSharedPreference sharedPreference;


    public CityAdapter(Context context, List<CityModel> locationObjects) {
        this.locationObjects = locationObjects;
        this.context = context;
    //    allRadioButton = new ArrayList<ViewEntityObject>();
    //    query = new DatabaseQuery(context);
    //    sharedPreference = new CustomSharedPreference(context);
    }

    @Override
    public CityListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityListHolder viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list, parent, false);
        viewHolder = new CityListHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CityListHolder holder, final int position) {
        holder.cityName.setText(locationObjects.get(position).getName());
        holder.tempStatus.setText(locationObjects.get(position).getmWeatherModel().get(0).getStatus());
        if (!locationObjects.get(position).getmWeatherModel().get(0).getIcon().isEmpty()) {
            Picasso.with(holder.itemView.getContext())
                    .load("http://openweathermap.org/img/w/"+locationObjects.get(position).getmWeatherModel().get(0).getIcon()+".png")
                    .into(holder.imageView);
        }
        holder.weatherInformation.setText(locationObjects.get(position).getmWeatherModel().get(0).getDay_temp()+" "+ (char) 0x00B0 );
        holder.tempMaxMIN.setText(locationObjects.get(position).getmWeatherModel().get(0).getMax_temp()+" "+ (char) 0x00B0 +" / "+locationObjects.get(position).getmWeatherModel().get(0).getMin_temp()+" "+ (char) 0x00B0 );
//        holder.deleteText.setTag(R.id.TAG_KEY, String.valueOf(locationObjects.get(position).getId()));
//
//        holder.deleteText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int databaseIndex = locationObjects.get(position).getId();
//                if(!holder.imageView.isChecked()){
////                    query.deleteLocation(databaseIndex);
////                    locationObjects.remove(position);
////                    notifyItemRemoved(position);
//                }
//            }
//        };

       // String buttonId = sharedPreference.getLocationInPreference();
    //    System.out.println("Stored id " + buttonId);

//        holder.imageView.setOnCheckedChangeListener(this);
//        setRadioButtonId(holder.imageView, position);
//        allRadioButton.add(new ViewEntityObject(holder.imageView, locationObjects.get(position).getLocationCity()));
//
//        String storedCityLocation = sharedPreference.getLocationInPreference();
//        if(allRadioButton.get(position).getRadioName().equals(storedCityLocation)){
//            holder.imageView.setChecked(true);
//        }

    }

    @Override
    public int getItemCount() {
        return this.locationObjects.size();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked){
            RadioButton radioButton = (RadioButton)compoundButton;
            int checkedRadioId = radioButton.getId();
//            for(int i = 0; i < allRadioButton.size(); i++){
////                if(allRadioButton.get(i).getRadioButton().getId() != checkedRadioId){
////                    allRadioButton.get(i).getRadioButton().setChecked(false);
////                }else{
////                    String name = allRadioButton.get(i).getRadioName();
////                    sharedPreference.setLocationInPreference(name);
////                }
//            }
        }

    }

    private void setRadioButtonId(RadioButton mButton, int position){
        if(position == 0){
            mButton.setId(idList[position]);
        }
        if(position == 1){
            mButton.setId(idList[position]);
        }
        if(position == 2){
            mButton.setId(idList[position]);
        }
        if(position == 3){
            mButton.setId(idList[position]);
        }
        if(position == 4){
            mButton.setId(idList[position]);
        }
    }
    public class CityListHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.city_name)
        public TextView cityName;
        @BindView(R.id.temp_info)
        public TextView weatherInformation;
        @BindView(R.id.temp_status)
        public TextView tempStatus;
        @BindView(R.id.temp_Max_min)
        public TextView tempMaxMIN;
       @BindView(R.id.weather_icon)
        public ImageView imageView;

        public CityListHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //deleteText.setTextColor(Color.RED);

        }
    }
}
