package pl.edu.pjwstk.mpr.proxy_v3;

public class KalkulatorProxy {
    String podaneHaslo;
    public KalkulatorProxy(String haslo) {
        this.podaneHaslo = haslo;

    }
    public int sumowanie(int a, int b) {
        Kalkulator x = new Kalkulator();
        if (podaneHaslo != "0000") {
            throw new RuntimeException("Password Error");
        }
        return x.sumowanie(a, b);
    }
}