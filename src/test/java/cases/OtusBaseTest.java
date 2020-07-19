package cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.WebDriverFactory;

import java.util.concurrent.TimeUnit;

public class OtusBaseTest {
    public Logger logger = LogManager.getLogger(OtusBaseTest.class);
    protected static WebDriver driver;
    public WebDriverFactory factory = new WebDriverFactory();

    public String browser = System.getProperty("browser");
    public String options = System.getProperty("options");
    public String myLogin = System.getProperty("myEmail");
    public String myPass = System.getProperty("myPassword");


    @Before
    public void setup() {
        /*// ---
        ChromeOptions options2 = new ChromeOptions();
        options2.addArguments("--headless");
        // ---*/
        if (options == null) {
            driver = factory.createDriver(browser);
        } else {
            driver = factory.createDriver(browser, options);
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @After
    public void closedown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed");
        }
    }
}
