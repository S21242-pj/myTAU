import org.example.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CarTest {
    private Car car;
    private Engine engine;
    private FuelTank fuelTank;
    private SunGlasses sunGlasses;
    private Weather weather;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        engine = Mockito.mock(Engine.class);
        fuelTank = Mockito.mock(FuelTank.class);
        sunGlasses = Mockito.mock(SunGlasses.class);
        weather = Mockito.mock(Weather.class);

        car = new Car(engine, fuelTank, sunGlasses, weather);
    }

    @Test
    public void isSunnyDay() {
        when(weather.todayIsSunny()).thenReturn(true);
        assertEquals(true, weather.todayIsSunny());

        when(weather.todayIsSunny()).thenReturn(false);
        assertEquals(false, weather.todayIsSunny());
    }

    @Test
    public void isRunning() {
        when(engine.isRunning()).thenReturn(true);
        assertEquals(true, car.isRunning());

        when(engine.isRunning()).thenReturn(false);
        assertEquals(false, car.isRunning());
    }

    @Test
    public void ifIsSunnyPutOnSunGlasses() {
        when(weather.todayIsSunny()).thenReturn(true);
        when(sunGlasses.takeSunGlasses()).thenReturn(true);
    }

    @Test
    public void ifItsRainyDayPutOffSunGlasses() {
        when(weather.todayIsSunny()).thenReturn(false);
        when(sunGlasses.takeSunGlasses()).thenReturn(false);
        car.prepareToRide();
    }


    @Test
    public void start() {
        when(engine.isRunning()).thenReturn(false, true);
        when(fuelTank.getFuel()).thenReturn(100);
        car.start();
    }

    @Test
    public void start_NoFuel() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("no fuel");
        when(engine.isRunning()).thenReturn(false);
        when(fuelTank.getFuel()).thenReturn(0);
        car.start();
    }

    @Test
    public void start_IsRunning() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("already running");
        when(fuelTank.getFuel()).thenReturn(100);
        when(engine.isRunning()).thenReturn(true);
        car.start();
    }

    @Test
    public void start_DidNotStart() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Started engine but isn't running");
        when(engine.isRunning()).thenReturn(false, false);
        when(fuelTank.getFuel()).thenReturn(100);
        car.start();
    }
}