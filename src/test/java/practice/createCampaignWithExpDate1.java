package practice;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createCampaignWithExpDate1 {

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
	     
	     //date after 30 days
	     Date date= new Date();
	     SimpleDateFormat sim = new SimpleDateFormat("dd=MM-yyyy");
	     sim.format(date);
	     Calendar cal= sim.getCalendar();
	     cal.add(Calendar.DAY_OF_MONTH, 30);
	     String daterequired = sim.format(cal.getTime());
	     
	     //create campaign
	     driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
	     driver.findElement(By.name("campaignName")).sendKeys("mock24"+randCount);
	    WebElement target = driver.findElement(By.name("targetSize"));
	    target.clear();
	    target.sendKeys("11");
	    
	    WebElement expCloseDate = driver.findElement(By.name("expectedCloseDate"));
	    Actions act=new Actions(driver) ;
	    act.click(expCloseDate).sendKeys(daterequired).perform();
	    
	    driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
	    
	    //validation
	    WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
	    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(toastmsg));
	   String  msg= toastmsg.getText();
	   if(msg.contains("mock24"))
	   {
		   System.out.println("campaign created");
	   }
	   else
	   {
		   System.out.println("campaign not created");
	   }
	   driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	   
	   //logout
	   
	   WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
	 
	   act.moveToElement(icon).perform();
	   WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
	   act.moveToElement(logout).click().perform();
	   driver.quit();
	   
	   
	}

	}


