package practice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;


public class PracticeProgram {
	
public static void main(String[] args) {
			/* sets the path for chromedriver */
			System.setProperty("webdriver.chrome.driver", "./drivers/edgedriver.exe");
			/* launches the edge browser*/
			WebDriver driver=new EdgeDriver();
			/* fetches the web app and waits until its loaded */
			driver.get("http://49.249.28.218:8098/");
			/* maximizes the browser window */
		     driver.manage().window().maximize();
			/* identifies the username and enters the data */
			WebElement username = driver.findElement(By.id("username"));
			username.sendKeys("rmgyantra");
			WebElement password = driver.findElement(By.id("inputPassword"));
			password.sendKeys("rmgy@9999");
			/* identifies the search button and clicks */
		    driver.findElement(By.className("btn btn-primary btn-lg btn-block")).click();
			}
}

	


