package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createContact {

	public static void main(String[] args) {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
        //login
	     driver.get("http://49.249.28.218:8098/");
	     driver.findElement(By.id("username")).sendKeys("rmgyantra");
	     driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	     driver.findElement(By.xpath("//button[text()='Sign In']")).click();
	     
	     Random ran= new  Random();
	     int randCount = ran.nextInt(1000);
	     
	     //create campaign
	     driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
	     driver.findElement(By.name("campaignName")).sendKeys("nagava"+randCount);
	    WebElement target = driver.findElement(By.name("targetSize"));
	    target.clear();
	    target.sendKeys("11");
	    driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
	    
	    //validation
	    WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
	    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.invisibilityOf(toastmsg));
	   String  msg= toastmsg.getText();
	   if(msg.contains("nagava"))
	   {
		   System.out.println("campaign created");
	   }
	   else
	   {
		   System.out.println("campaign not created");
	   }
	   driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	   
	   //contacts
	   
	   driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	   driver.findElement(By.name("organizationName")).sendKeys("raj groups");
	   driver.findElement(By.name("title")).sendKeys("steel");
	   driver.findElement(By.name("contactName")).sendKeys("anna");
	   driver.findElement(By.name("mobile")).sendKeys("9625678932");
	   driver.findElement(By.xpath("(//button[@type='button'])[2]"));


	   

	   
	}

}
