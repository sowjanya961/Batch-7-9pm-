package Product;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebdriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Homepage;
import objectRepositories.Productpage;

public class ProductTest extends BaseClass {

	@Test(groups="smoke")
	public void createProductWithMandatoryFields() throws IOException {
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebdriverUtility wutil = new WebdriverUtility();

		// to read data from excel
		String prodname = eutil.toReadDataFromExcelFile("Product", 1, 0);
		String quant = eutil.toReadDataFromExcelFile("Product", 1, 1);
		String cost = eutil.toReadDataFromExcelFile("Product", 1, 2);

		// create product
		Homepage hp = new Homepage(driver);
		hp.getProductslnk().click();

		Productpage pp = new Productpage(driver);
		pp.getAddProductBtn().click();
		pp.getProductName().sendKeys(prodname + jutil.togetRandomNumber());
		WebElement categorydropdown = pp.getCategoryDd();
		// dropdown1
		wutil.select(categorydropdown, 3);

		WebElement quantity = pp.getQuantityTb();
		quantity.clear();
		quantity.sendKeys(quant);

		WebElement price = pp.getPriceTb();
		price.clear();
		price.sendKeys(cost);

		WebElement vendordropdown = pp.getVendorDd();
		// dropdown2
		wutil.select(vendordropdown, 1);

		pp.getAddProductSubmitBtn().click();

		// validation
		Campaignpage cp = new Campaignpage(driver);
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		if (msg.contains(prodname)) {
			System.out.println("product is added");
		} else {
			System.out.println("product is not added ");
		}
		cp.getClosemsg().click();
	}

}
