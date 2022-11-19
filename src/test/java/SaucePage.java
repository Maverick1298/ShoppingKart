import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;


public class SaucePage {
    String URL;
    String inputFilePath;
   static WebDriver driver;
    @BeforeTest
    public void getURL(){

        URL="https://www.saucedemo.com/";
        driver=DriversManager.getDriver("edge");
        inputFilePath="src/test/java/inputFiles/sauceInputFile1.xlsx";


    }
    @Test
    public void loginAction(){
        LoginPage logObj=PageFactory.initElements(driver,LoginPage.class);// initializing LoginPage Class
        logObj.loginAction("standard_user","secret_sauce",URL);
    }
    @Test(dependsOnMethods = "loginAction")
    public void addItems() throws InterruptedException {
        InputFileReader readerObj=new InputFileReader(inputFilePath);
        readerObj.setSheet("Sheet1");
        OrderProduct prod=new OrderProduct(SaucePage.driver,readerObj);
        prod.orderItem();//order products
    }

}
