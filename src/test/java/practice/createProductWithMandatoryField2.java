package practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createProductWithMandatoryField2 {

	public static void main(String[] args) throws Throwable {
		// To read data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commondata.properties");
		Properties prop = new Properties();
		prop.load(fis);

		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		Random ran = new Random();
		int randcount = ran.nextInt(1000);

		// To read data from excel
		FileInputStream fis1 = new FileInputStream(".\\src\\test\\resources\\TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Product");
		 //Row r = sh.getRow(1);
		String prodname = sh.getRow(1).getCell(0).getStringCellValue();
		String quant = sh.getRow(1).getCell(1).getStringCellValue();
		String cost = sh.getRow(1).getCell(2).getStringCellValue();

		// create product
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productName")).sendKeys(prodname+ randcount);

		WebElement categorydropdown = driver.findElement(By.name("productCategory"));
		// dropdown1
		Select drop1 = new Select(categorydropdown);
		drop1.selectByValue("Electronics");

		WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys(quant);

		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys(cost);

		WebElement vendordropdown = driver.findElement(By.name("vendorId"));
		// dropdown2
		Select drop2 = new Select(vendordropdown);
		drop2.selectByValue("VID_001");

		driver.findElement(By.xpath("//button[text()='Add']")).click();

		// validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();
		if (msg.contains(prodname)) {
			System.out.println("product is added");
		} else {
			System.out.println("product is not added ");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

//logout

		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act = new Actions(driver);
		act.moveToElement(icon).perform();
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act.moveToElement(logout).click().perform();
		driver.quit();

	}

}
