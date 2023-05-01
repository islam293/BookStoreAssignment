package QAdemoUIModule.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*This class contains all the methods and steps performed in the Landing Page.*/
public class LandingPage extends PageBase{

    //create constructor for the webDriver
    public LandingPage(WebDriver driver) {super(driver);}

    //Declare all elements used in the page
    By bookStoreApplication = By.xpath("//div[@class='card-body']/h5[normalize-space(.)='Book Store Application']");
    By header = By.xpath("//div[@class='pattern-backgound playgound-header']/div[@class='main-header']");

    //Click and open the book store application page
    public String OpenAppPage(){
        clickButtonJS(bookStoreApplication);
        return getTxt(header);
    }
}
