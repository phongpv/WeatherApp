package com.example.weatherapp;

import java.io.Serializable;

/**
 * Created by PhongPham on 9/21/2017.
 */

public class Weather implements Serializable{
    private String day;
    private String cenciusMin;
    private String cenciusMax;
    private String state;
    private String pressure;
    private String description;
    private int hinh;

    public Weather(String day, String cenciusMin, String cenciusMax, String state, String pressure, String description, int hinh) {
        this.day = day;
        this.cenciusMin = cenciusMin;
        this.cenciusMax = cenciusMax;
        this.state = state;
        this.pressure = pressure;
        this.description = description;
        this.hinh = hinh;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCenciusMin() {
        return cenciusMin;
    }

    public void setCenciusMin(String cenciusMin) {
        this.cenciusMin = cenciusMin;
    }

    public String getCenciusMax() {
        return cenciusMax;
    }

    public void setCenciusMaz(String cenciusMax) {
        this.cenciusMax = cenciusMax;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
