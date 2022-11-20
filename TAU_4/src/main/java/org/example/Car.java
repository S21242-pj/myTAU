package org.example;


public class Car {

    private Weather weather;
    private SunGlasses sunGlasses;
    private Engine engine;
    private FuelTank fuelTank;

    public Car(Engine engine, FuelTank fuelTank, SunGlasses sunGlasses, Weather weather) {
        this.engine = engine;
        this.fuelTank = fuelTank;
        this.sunGlasses = sunGlasses;
        this.weather = weather;
    }

    public void prepareToRide() {

        weather.sunny();

        if(weather.todayIsSunny()) {
            throw new IllegalStateException("So sunny day today, isn't it? Where are my sun glasses!");
        }

        if(weather.todayIsSunny()){
            sunGlasses.putOnSunGlasses();
        } else {
            sunGlasses.putOffSunGlasses();
        }

        if(sunGlasses.takeSunGlasses()) {
            throw new IllegalStateException("Now is better!");
        }
    }

    public void start() {
        if(engine.isRunning()) {
            throw new IllegalStateException("Engine already running");
        }
        if(fuelTank.getFuel() == 0) {
            throw new IllegalStateException("Can't start: no fuel");
        }

        engine.start();

        if(!engine.isRunning()) {
            throw new IllegalStateException("Started engine but isn't running");
        }
    }

    public boolean isRunning() {
        return engine.isRunning();
    }
}