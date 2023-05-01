package QAdemoUIModule.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*This is the abstract page base class  where all other classes are inherited from.
      It contains all the methods and variables which are common and will be used on all pages.*/
public class PageBase {

    // Declaration the webDriver and the wait time that will be used.
    protected static WebDriver driver;
    protected WebDriverWait wait;

    // Initialize the driver and set the explicit wait time
    public PageBase(WebDriver driver){
        this.driver =driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //A method to wait until the page loaded before searching for elements
    protected void waitMethod(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    //A method to return the web element for further actions
    protected WebElement getWebElement(By ByName) {
        WebElement element = driver.findElement(ByName);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    //A method to click on a button after checking its visibility and clickabilit
    protected void clickButton(By buttonName) {
        WebElement button = driver.findElement(buttonName);
        wait.until(ExpectedConditions.visibilityOf(button));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    /*A method to click on a button with JavaScript executor after checking its visibility and clickability.
    * It uses By element to perform the action*/
    protected void clickButtonJS(By buttonName) {
        WebElement button = driver.findElement(buttonName);
        wait.until(ExpectedConditions.visibilityOf(button));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", button);
    }

    /*A method to click on a button with JavaScript executor after checking its visibility and clickability.
     * It uses Web element to perform the action*/
    protected void clickButtonJS(WebElement buttonName) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", buttonName);
    }

    // A method to type text in a field after clearing the initial text if exists
    protected void sendText (By textFieldName, String value) {
        WebElement textField = driver.findElement(textFieldName);
        wait.until(ExpectedConditions.visibilityOf(textField));
        textField.clear();
        textField.sendKeys(value);
    }

    // A method to get a text from field after scrolling to it
    protected String getTxt(By elementName){
        WebElement element = driver.findElement(elementName);
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String text = element.getText();
        if (text.isEmpty() || text == null){
            System.out.println( "Text msg is empty.");
        }
        return text;
    }

}