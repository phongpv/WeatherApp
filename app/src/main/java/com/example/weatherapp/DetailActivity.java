package com.example.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView txtState, txtDescription, txtTemp, txtWindSpeed, txtHumidity, txtSeaLv, txtGrandLv;
    ImageView imgHinh;
    String mState,mDescription,mTemp,mWind,mHumidity,mSeaLv,mGrandLv;
    int mHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        anhXa();
        getValue();
        setValue();
    }
    //Anh xa
    public void anhXa(){
        txtState = (TextView) findViewById(R.id.txtState);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtTemp = (TextView) findViewById(R.id.txtTemp);
        txtWindSpeed = (TextView) findViewById(R.id.txtWindSpeed);
        txtHumidity = (TextView) findViewById(R.id.txtNumHumidity);
        txtSeaLv = (TextView) findViewById(R.id.txtNumSeaLv);
        txtGrandLv = (TextView) findViewById(R.id.txtNumGrandLv);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
    }
    //get value intent
    public void getValue(){
        Intent intent = getIntent();
        mState = intent.getStringExtra("state");
        mDescription = intent.getStringExtra("description");
        mWind = intent.getStringExtra("wind");
        mTemp = intent.getStringExtra("temp");
        mHumidity = intent.getStringExtra("humidity");
        mSeaLv = intent.getStringExtra("sea_lv");
        mGrandLv = intent.getStringExtra("grand_lv");
        mHinh = intent.getIntExtra("hinh",-1);
    }
    //set value
    public void setValue(){
        txtState.setText(mState);
        txtDescription.setText(mDescription);
        txtTemp.setText(mTemp);
        txtWindSpeed.setText(mWind);
        txtHumidity.setText(mHumidity);
        txtSeaLv.setText(mSeaLv);
        txtGrandLv.setText(mGrandLv);
        imgHinh.setImageResource(mHinh);
    }

}
