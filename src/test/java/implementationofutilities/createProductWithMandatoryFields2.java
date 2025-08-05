package implementationofutilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebdriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Homepage;
import objectRepositories.Loginpage;
import objectRepositories.Productpage;

public class createProductWithMandatoryFields2 {

	public static void main(String[] args) throws IOException {
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebdriverUtility wutil = new WebdriverUtility();

		String BROWSER = putil.togetDataFromPropertiesFile("Browser");
		String URL = putil.togetDataFromPropertiesFile("Url");
		String USERNAME = putil.togetDataFromPropertiesFile("Username");
		String PASSWORD = putil.togetDataFromPropertiesFile("Password");

		String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
		String target = eutil.toReadDataFromExcelFile("Campaign", 1, 3);

		// actual script
		WebDriver driver = null;
		if (BROWSER.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("Firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		wutil.waitForPageToLoad(driver);
		driver.get(URL);

		// login
		Loginpage lp = new Loginpage(driver);
		lp.getUNTb().sendKeys(USERNAME);
		lp.getPWTb().sendKeys(PASSWORD);
		lp.getLoginBtn().click();

		String prodname = eutil.toReadDataFromExcelFile("Product", 1, 0);
		String quant = eutil.toReadDataFromExcelFile("Product", 1, 1);
		String cost = eutil.toReadDataFromExcelFile("Product", 1, 2);

		// create product
		Homepage hp = new Homepage(driver);
		hp.getProductslnk().click();

		Productpage pp = new Productpage(driver);
		pp.getAddProductBtn().click();
		pp.getProductName().sendKeys(prodname+jutil.togetRandomNumber());
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

		// logout
		WebElement icon = hp.getUserIcon();
		wutil.mouseOver(driver, icon);
		WebElement logout = hp.getLogoutbtn();
		wutil.clickOnWebElement(driver, logout);

		// closes browser
		driver.quit();

	}

}
