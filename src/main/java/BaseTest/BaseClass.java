package BaseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import genericUtility.PropertiesFileUtility;
import genericUtility.WebdriverUtility;
import objectRepositories.Homepage;
import objectRepositories.Loginpage;


public class BaseClass {
	public WebDriver driver = null;
	public static WebDriver sdriver=null;//listeners
	PropertiesFileUtility putil = new PropertiesFileUtility();
	WebdriverUtility wutil = new WebdriverUtility();

	@BeforeSuite(groups={"smoke","regression"})
	public void beforeSuite() {
		System.out.println("DB Connectivity open");
	}
	
	 @BeforeTest(groups={"smoke","regression"})
	  public void beforeTest() {
		  System.out.println("Pre Conditions");
	  }

     //@Parameters("BROWSER")
	@BeforeClass(groups={"smoke","regression"})
	public void beforeClass() throws IOException {//beforeClass(String browser)
    	 //String BROWSER=browser;
		String BROWSER = putil.togetDataFromPropertiesFile("Browser");
		if (BROWSER.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("Firefox")) {
			driver = new FirefoxDriver();
		}
		sdriver=driver;//passing driver reference to static driver variable
		System.out.println("Launching Browser");
	}

	@BeforeMethod(groups={"smoke","regression"})
	public void beforeMethod() throws IOException {
		String URL = putil.togetDataFromPropertiesFile("Url");
		String USERNAME = putil.togetDataFromPropertiesFile("Username");
		String PASSWORD = putil.togetDataFromPropertiesFile("Password");
		driver.get(URL);
		Loginpage lp = new Loginpage(driver);
		lp.getUNTb().sendKeys(USERNAME);
		lp.getPWTb().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
		driver.manage().window().maximize();
		wutil.waitForPageToLoad(driver);
		System.out.println("Login");
	}

	@AfterMethod(groups={"smoke","regression"})
	public void afterMethod() {
		Homepage hp = new Homepage(driver);
		WebElement icon = hp.getUserIcon();
		wutil.mouseOver(driver, icon);
		WebElement logout = hp.getLogoutbtn();
		wutil.clickOnWebElement(driver, logout);
		System.out.println("Logout");
	}

	@AfterClass(groups={"smoke","regression"})
	public void afterClass() {
		driver.quit();
		System.out.println("Closing Browser");
	}
	
	@AfterTest(groups={"smoke","regression"})
	  public void afterTest() {
		  System.out.println("Post Conditions");
	  }

	@AfterSuite(groups={"smoke","regression"})
	public void afterSuite() {
		System.out.println("DB Connectivity Close");
	}

}
