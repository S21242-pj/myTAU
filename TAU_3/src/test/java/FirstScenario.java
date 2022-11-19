import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstScenario {


    private WebDriver googleDriver;
    private WebDriver fireFoxDriver;

    private final static String EXPECTED_TITLE = "Najgorsze obrazki z sieci!";
    private final static String URL = "https://jbzd.com.pl/";
    private final static String COOCKIE_DIALOG = "UI__root___2R-t4o";
    private final static String LOGIN_FORM_URL = "https://jbzd.com.pl/";
    private final static String RESET_PASS_ANCHOR = "data-v-7da15af0";
    private final static String FAKE_EMAIL = "someFake@email.pl";
    private final static String RESET_PASSWORD_INPUT = "ctl00_ContentPlaceHolder1_tbLogin";
    private final static String RESET_USER_NOT_FOUND_DIV = "form-group error";
    private final static String RESET_USER_NOT_FOUND_VALUE = "Zaznaczony email jest nieprawidłowy.";

    @BeforeAll
    static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setup() {
        googleDriver = new ChromeDriver();
        fireFoxDriver = new FirefoxDriver();
    }

    @AfterEach
    public void close() {
        googleDriver.quit();
        fireFoxDriver.quit();
    }

    @Test
    public void resetPassForUserNotRegisteredChrome() throws InterruptedException {
        //CHROME
        String actualTitle = "";
        googleDriver.get(URL);

        actualTitle = googleDriver.getTitle();
        Assertions.assertEquals(actualTitle, EXPECTED_TITLE);

        try {
            WebDriverWait wait = new WebDriverWait(googleDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(COOCKIE_DIALOG)));
        } catch (TimeoutException ignored) {
        }
        if (googleDriver.findElement(By.id(COOCKIE_DIALOG)).isDisplayed()) {
            googleDriver.findElement(By.id("c-p-bn")).click(); //accept all
        }
        Thread.sleep(200);
        try {
            WebDriverWait wait = new WebDriverWait(googleDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(RESET_PASS_ANCHOR)));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: " + RESET_PASS_ANCHOR + " not found");
        }
        Assertions.assertEquals(googleDriver.getCurrentUrl(), LOGIN_FORM_URL);
        googleDriver.findElement(By.id(RESET_PASS_ANCHOR)).click();
        try {
            WebDriverWait wait = new WebDriverWait(googleDriver, Duration.ofMillis(15000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(RESET_USER_NOT_FOUND_DIV)));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: " + RESET_USER_NOT_FOUND_DIV + " not found");
        }
        String errNotFoundMsg = googleDriver.findElement(By.id(RESET_USER_NOT_FOUND_DIV)).getText();
        Assertions.assertEquals(errNotFoundMsg, RESET_USER_NOT_FOUND_VALUE);
    }

    @Test
    public void resetPassForUserNotRegisteredFirefox() throws InterruptedException {
        //FIREFOX
        String actualTitle = "";
        fireFoxDriver.get(URL);

        actualTitle = fireFoxDriver.getTitle();
        Assertions.assertEquals(actualTitle, EXPECTED_TITLE);

        try {
            WebDriverWait wait = new WebDriverWait(fireFoxDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(RESET_PASS_ANCHOR)));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: " + RESET_PASS_ANCHOR + " not found");
        }
        Assertions.assertEquals(fireFoxDriver.getCurrentUrl(), LOGIN_FORM_URL);
        fireFoxDriver.findElement(By.id(RESET_PASS_ANCHOR)).click();
        WebElement resetLoginInputField = fireFoxDriver.findElement(By.id(RESET_PASSWORD_INPUT));
        resetLoginInputField.sendKeys(FAKE_EMAIL);
        resetLoginInputField.sendKeys(Keys.ENTER);
        try {
            WebDriverWait wait = new WebDriverWait(fireFoxDriver, Duration.ofMillis(15000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(RESET_USER_NOT_FOUND_DIV)));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: " + RESET_USER_NOT_FOUND_DIV + " not found");
        }
        String errNotFoundMsg = fireFoxDriver.findElement(By.id(RESET_USER_NOT_FOUND_DIV)).getText();
        Assertions.assertEquals(errNotFoundMsg, RESET_USER_NOT_FOUND_VALUE);
    }











}
