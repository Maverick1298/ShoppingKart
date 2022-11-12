import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    @FindBy(how = How.ID,using = "user-name")
   private WebElement usrName;
    @FindBy(how=How.ID,using = "password")
    private WebElement password;
    @FindBy(how=How.NAME,using = "login-button")
   private WebElement loginButton;
    public void loginAction(String user,String pass,String URL){
        driver.get(URL);
        usrName.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
        Assert.assertEquals(driver.getTitle(),"Swag Labs","Page not loaded");
        Reporter.log("Navigate to Products Page");
    }
}
