package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/*A class that contains all the methods to run the test */
public class TestBase {

    //Declare the Driver
    public static WebDriver driver;
    protected WebDriverWait wait;

    //Open the browser for UI Test
    public void startDriver() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    /* open the required page before any test cases to make sure
     that the steps are the same for every test case */
    public void navigateToURL() {
        String url= "https://demoqa.com/";
        driver.navigate().to(url);
        /* Set implicit time for the driver to wait after opening the driver to allow
        the page to be fully loaded */
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    //Close the browser after finishing the test case to clear cache
    public void closeBrowser(){
        driver.quit();
    }
}
