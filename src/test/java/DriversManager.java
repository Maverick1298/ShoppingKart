import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class DriversManager {

    public static WebDriver getDriver(String browser){
        WebDriver driver=null;
        if (browser.equalsIgnoreCase("chrome")){
           WebDriverManager.chromedriver().setup();
           driver=new ChromeDriver();
        }
       else if(browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }
       else if (browser.equalsIgnoreCase("mozilla")){
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }
        return driver;
    }
}
