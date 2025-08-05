package implementationofutilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebdriverUtility;

public class createProductWithMandatoryFields1 {

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
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		
		String prodname = eutil.toReadDataFromExcelFile("Product", 1, 0);
		String quant = eutil.toReadDataFromExcelFile("Product", 1, 1);
		String cost = eutil.toReadDataFromExcelFile("Product", 1, 2);
		// create product
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productName")).sendKeys(prodname + jutil.togetRandomNumber());

		WebElement categorydropdown = driver.findElement(By.name("productCategory"));
		// dropdown1
		wutil.select(categorydropdown, 3);

		WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys(quant);

		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys(cost);

		WebElement vendordropdown = driver.findElement(By.name("vendorId"));
		// dropdown2
		wutil.select(vendordropdown, 1);

		driver.findElement(By.xpath("//button[text()='Add']")).click();

		// validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		if (msg.contains(prodname)) {
			System.out.println("product is added");
		} else {
			System.out.println("product is not added ");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		// logout

		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		wutil.mouseOver(driver, icon);
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		wutil.clickOnWebElement(driver, logout);

		// closes browser
		driver.quit();

	}

}
