package com.example.weatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherAdapter.WeatherOnTtemListener{
    RecyclerView recyclerView;
    WeatherAdapter adapter;
    List<Weather> list;
    Weather weather;
    String  day, cenciusMin, cenciusMax, state, pressure, desciption ,temp, wind_speed, humidity, seaLv, grandLv;
    List<String> mState,mDescription,mTemp,mWind,mHumidity,mSeaLv,mGrandLv;
    List<Integer> mHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createList();
        initView();
        initData();
        new getData().execute("https://api.openweathermap.org/data/2.5/forecast/?lat=21.0090602&lon=105.8324736&mode=json&units=metric&cnt=7&APPID=f99f7329e28b995c7bfd1a33f191b59b");
    }
    //create data
    public void initData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new WeatherAdapter(this, this);
    }
    //create view
    public void initView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
    }

    //create list
    public void createList(){
        list = new ArrayList<>();
        mState = new ArrayList<>();
        mDescription = new ArrayList<>();
        mTemp = new ArrayList<>();
        mWind = new ArrayList<>();
        mHumidity = new ArrayList<>();
        mSeaLv = new ArrayList<>();
        mGrandLv = new ArrayList<>();
        mHinh = new ArrayList<>();
    }

    //onClick
    @Override
    public void onClick(int position) {
        Weather mWeather = list.get(position);
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("state",mState.get(position));
        intent.putExtra("description",mDescription.get(position));
        intent.putExtra("hinh",mHinh.get(position));
        intent.putExtra("temp",mTemp.get(position));
        intent.putExtra("wind",mWind.get(position));
        intent.putExtra("humidity",mHumidity.get(position));
        intent.putExtra("sea_lv",mSeaLv.get(position));
        intent.putExtra("grand_lv",mGrandLv.get(position));
        startActivity(intent);
    }

    //get data
    public class getData extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                // create a list jsonobject
                List<JSONObject> object = new ArrayList<>();
                for(int i = 0; i< jsonArray.length();i++){
                    object.add(jsonArray.getJSONObject(i));
                }
                int k = 0;
                int image = 0;
                for (int j = 0; j< object.size(); j++){
                    day = object.get(j).getString("dt_txt");
                    JSONObject main = object.get(j).getJSONObject("main");
                    int tempTB = (int)Double.parseDouble(main.getString("temp"));
                    cenciusMin = main.getString("temp_min") + "ºC";
                    cenciusMax = main.getString("temp_max") + "ºC";
                    temp = tempTB+"";
                    pressure = "- Pressure: " +  main.getString("pressure");
                    seaLv = main.getString("sea_level");
                    grandLv = main.getString("grnd_level");
                    humidity = main.getString("humidity")+"%";

                    JSONArray a = object.get(j).getJSONArray("weather");
                    JSONObject ao = a.getJSONObject(0);
                    state = ao.getString("main");
                    desciption = "Description: " + ao.getString("description");

                    JSONObject wind = object.get(j).getJSONObject("wind");
                    wind_speed = wind.getString("speed");


                    if(state.equalsIgnoreCase("Clouds")){
                        image = R.drawable.cloud;
                    }
                    if(state.equalsIgnoreCase("Rain")){
                        image = R.drawable.rain;
                    }
                    if(state.equalsIgnoreCase("Clear")){
                        image = R.drawable.clear;
                    }

                    //add state to list
                    mTemp.add(temp);
                    mWind.add(wind_speed);
                    mHumidity.add(humidity);
                    mSeaLv.add(seaLv);
                    mGrandLv.add(grandLv);
                    mState.add(state);
                    mDescription.add(desciption);
                    mHinh.add(image);

                    weather = new Weather(day,cenciusMin, cenciusMax,state,pressure,desciption,image);
                    list.add(weather);
                }
                adapter.setmList(list);
                recyclerView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
