package pl.edu.pjwstk.mpr.proxy_v3;

public class Demo {
    public static void main(String[] args) {
        var Kalkulator = new Kalkulator();

        System.out.println(Kalkulator.sumowanie(3,3));


        var KalkulatorProxy = new KalkulatorProxy("0000");
        System.out.println(KalkulatorProxy.sumowanie(4,4));

    }
}
