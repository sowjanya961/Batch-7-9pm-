package implementationofutilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebdriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Homepage;
import objectRepositories.Loginpage;

public class createCampaignWithStatus2 {

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

		// create campaign
		Homepage hp = new Homepage(driver);
		hp.getCreateCampaignbtn().click();

		Campaignpage cp = new Campaignpage(driver);
		cp.getCampaignNameTb().sendKeys(campname);
		cp.getCampaignStatusTb().sendKeys("pass");
		WebElement size = cp.getTargetSizeTb();
		size.clear();
		size.sendKeys(target);
		cp.getCreateCampaignSubmitBtn().click();

		// validation
		WebElement toastmsg = cp.getToastmsg();

		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		if (msg.contains(campname)) {
			System.out.println("campaign created");
		} else {
			System.out.println("campaign not created");
		}
		cp.getClosemsg().click();

		// logout

		WebElement icon = hp.getUserIcon();
		wutil.mouseOver(driver, icon);
		WebElement logout = hp.getLogoutbtn();
		wutil.clickOnWebElement(driver, logout);

		// close browser
		driver.quit();

	}

}
