import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;

public class SecondScenarios {

    private WebDriver driver;
    private StringBuffer errors = new StringBuffer();

    @Before
    public void setup() throws Exception {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\SzyDe\\OneDrive\\Pulpit\\Studia\\Semestr_7\\TAU\\laborki-3\\chromedriver-107.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void isThereSomeControlWarriorDeckFromCUFFIOPlayer() throws Exception {
        driver.get("https://hearthstone-decks.net/");
        driver.findElement(By.id("searchform")).click();
        driver.findElement(By.id("searchform")).sendKeys("Control Warrior");
        driver.findElement(By.id("searchform")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//a[@href=\"//control-warrior-78-legend-cuffio-score-12-3/\"]")).click();
        // Skopuj deck code
        driver.findElement(By.id("#Code1")).click();
    }

    @Test
    public void findTheBestMemesInWaitingSectionFromTheLast24Hours() throws Exception {
        driver.get("https://jbzd.com.pl/");
        driver.findElement(By.name("ZAAKCEPTUJ WSZYSTKO")).click();
        driver.findElement(By.xpath("//a[@href=\"oczekujace\"]")).click();
        driver.findElement(By.name(" Top+ ")).click();
        driver.findElement(By.name("24h")).click();
    }

    @After
    public void teardown() throws Exception {
        driver.quit();
        String verificationErrorString = errors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}