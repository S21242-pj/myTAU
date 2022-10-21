package pl.edu.pjwstk.mpr.proxyV4;

import pl.edu.pjwstk.mpr.proxyV4.Spaceship;

public class MillenniumFalcon implements Spaceship {
    @Override
    public void fly() {
        System.out.println("Welcome, Han. The Millennium Falcon is starting up its engines!");
    }
}