package pl.edu.pjwstk.mpr.proxyV4;

public class demo {
    public static void main(String[] args) {
        Spaceship falcon1 = new MillenniumFalconProxy(new Pilot("Han Solo"));
        falcon1.fly();

        Spaceship falcon2 = new MillenniumFalconProxy(new Pilot("Jabba the Hutt"));
        falcon2.fly();
    }
}
