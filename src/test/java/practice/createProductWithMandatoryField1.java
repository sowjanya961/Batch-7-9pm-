package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createProductWithMandatoryField1 {

	public static void main(String[] args) {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
        //login
	     driver.get("http://49.249.28.218:8098/");
	     driver.findElement(By.id("username")).sendKeys("rmgyantra");
	     driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	     driver.findElement(By.xpath("//button[text()='Sign In']")).click();
	     
	     Random ran = new Random();
	     int randcount = ran.nextInt(1000);

	     //create product
	     driver.findElement(By.xpath("//a[text()='Products']")).click();
	     driver.findElement(By.xpath("//span[text()='Add Product']")).click();
	     driver.findElement(By.name("productName")) .sendKeys("oneplus"+randcount);
	     
	     WebElement categorydropdown = driver.findElement(By.name("productCategory"));
	     //dropdown1
	     Select drop1= new  Select(categorydropdown);
	     drop1.selectByValue("Electronics");
	     
	    WebElement quantity = driver.findElement(By.name("quantity"));
	    quantity.clear();
	    quantity.sendKeys("18");
	    
	    WebElement price = driver.findElement(By.name("price"));
	    price.clear();
	    price.sendKeys("250");
	    
	   WebElement vendordropdown = driver.findElement(By.name("vendorId"));
	   //dropdown2
	   Select drop2=new Select(vendordropdown);
	   drop2.selectByValue("VID_001");
	   
	   driver.findElement(By.xpath("//button[text()='Add']")).click();
	   
	   //validation
	    WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
	    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(toastmsg));
	   String  msg= toastmsg.getText();
	   if(msg.contains("Successfully"))
	   {
		   System.out.println("product is added");
	   }
	   else
	   {
		   System.out.println("product is not added ");
	   }
	   driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	   
//logout
	   
	   WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
	   Actions act=new Actions(driver);
	   act.moveToElement(icon).perform();
	   WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
	   act.moveToElement(logout).click().perform();
	   driver.quit();

	}
	
	

}
