import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class OrderProduct {
    WebDriver driver;
    InputFileReader readerObj;
    public OrderProduct(WebDriver driver,InputFileReader readerObj) {
        this.driver = driver;
        this.readerObj=readerObj;
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.ID, using = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackPack;
    @FindBy(how = How.ID, using = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement addTshirt;
    @FindBy(how = How.ID, using = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLight;
    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    private WebElement cartIcon;
    @FindBy(how = How.ID, using = "checkout")
    private WebElement checkOutButtn;
    @FindBy(how = How.ID, using = "remove-sauce-labs-backpack")
    private WebElement removeBackpack;
    @FindBy(how = How.ID, using = "remove-sauce-labs-t-shirt")
    private WebElement removetShirt;
    @FindBy(how = How.NAME, using = "continue-shopping")
    private WebElement continueShopping;
    @FindBy(how = How.ID, using = "first-name")
    private WebElement nameField;
    @FindBy(how = How.ID, using = "last-name")
    private WebElement lastName;
    @FindBy(how = How.ID, using = "postal-code")
    private WebElement zipCode;
    @FindBy(how = How.ID, using = "continue")
    private WebElement continueBtn;
    @FindBy(how=How.CLASS_NAME,using = "summary_subtotal_label")
    private WebElement itemTotal;
    @FindBy(how=How.CLASS_NAME,using = "summary_tax_label")
    private WebElement tax;
    @FindBy(how=How.CLASS_NAME,using = "summary_total_label")
    private WebElement totalAmount;
    @FindBy(how=How.ID,using = "finish")
    private WebElement finishBtn;
    @FindBy(how=How.NAME,using = "back-to-products")
    private WebElement backToHomeBtn;

    int rows = 0;
    int cols = 0;

    public void orderItem() throws InterruptedException {

        rows = readerObj.rows;
        cols = readerObj.cols;
        for (int row = 1; row <= rows; row++) {
            String item1 = "";
            String item2 = "";
            String item3 = "";
            item1 = readerObj.getCellData(row, "Item1");
            if (item1.equalsIgnoreCase("backpack")) {
                addBackPack.click();
            } else if (item1.equalsIgnoreCase("tshirt")) {
                addTshirt.click();
            } else if (item1.equalsIgnoreCase("bikelight")) {
                addBikeLight.click();
            }
            item2 = readerObj.getCellData(row, "Item2");
            if (item2.equalsIgnoreCase("backpack")) {
                addBackPack.click();
            } else if (item2.equalsIgnoreCase("tshirt")) {
                addTshirt.click();
            } else if (item2.equalsIgnoreCase("bikelight")) {
                addBikeLight.click();
            }
            item3 = readerObj.getCellData(row, "Item3");
            if (item3.equalsIgnoreCase("backpack")) {
                addBackPack.click();
            } else if (item3.equalsIgnoreCase("tshirt")) {
                addTshirt.click();
            } else if (item3.equalsIgnoreCase("bikelight")) {
                addBikeLight.click();
            }
            else if  (item1.equalsIgnoreCase(null) || item2.equalsIgnoreCase(null) || item3.equalsIgnoreCase(null))
            {
                System.out.println();
            }

            cartIcon.click();
            checkOutButtn.click();
            System.out.println("checkoutPage...");
            String fName;
            fName=readerObj.getCellData(row,"firstName");
            nameField.sendKeys(fName);
            String lName;
            lName=readerObj.getCellData(row,"lastName");
            lastName.sendKeys(lName);
            String zip;
            zip=readerObj.getCellData(row,"zipCode");
            zipCode.sendKeys(String.valueOf(zip));

            continueBtn.click();

            System.out.println("Hi "+fName+" your product will be delivered to "+Double.valueOf(zip));
            String[] totalItem=itemTotal.getText().split(":");
            readerObj.setCellData(row,"itemTotal",totalItem[1].trim());
            String[] taxVal=tax.getText().split(":");
            readerObj.setCellData(row,"Tax",taxVal[1].trim());
            String [] total=totalAmount.getText().split(":");
            readerObj.setCellData(row,"Total",total[1].trim());
            finishBtn.click();
            backToHomeBtn.click();

            }
        readerObj.closeFile();
        driver.close();

    }

}
