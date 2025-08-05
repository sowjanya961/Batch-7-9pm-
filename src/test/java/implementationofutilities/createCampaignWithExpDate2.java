package implementationofutilities;

import java.io.IOException;

import org.openqa.selenium.By;
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

public class createCampaignWithExpDate2 {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		Loginpage lp = new Loginpage(driver);
		lp.getUNTb().sendKeys(USERNAME);
		lp.getPWTb().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
        
		int ran = jutil.togetRandomNumber();
		// date after 30 days
		String daterequired = jutil.togetRequiredDate(30);

		// create campaign
		Homepage hp = new Homepage(driver);
		hp.getCreateCampaignbtn().click();
		Campaignpage cp = new Campaignpage(driver);
		cp.getCampaignNameTb().sendKeys(campname+ran);
		WebElement size = cp.getTargetSizeTb();
		size.clear();
		size.sendKeys(target);
		Thread.sleep(2000);
		
		WebElement expclosedate = cp.getDateTb();
		wutil.passInput(driver, expclosedate, daterequired);
		
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
		cp.getClosemsg().click();;
		// logout

		WebElement icon = hp.getUserIcon();
		wutil.mouseOver(driver, icon);
		WebElement logout = hp.getLogoutbtn();
		wutil.clickOnWebElement(driver, logout);

		// closes the browser
		driver.quit();

	}

}
