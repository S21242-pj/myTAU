package org.example;

public class Weather {

    private boolean weather;

    public boolean todayIsSunny(){
        return weather;
    }

    public void sunny() {
        weather = true;
    }

    public void rainy() {
        weather = false;
    }

}