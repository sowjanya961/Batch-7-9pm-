package objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

	@FindAll({@FindBy(id="username"),@FindBy(name="username")})
	private WebElement UNTb;
	
	@FindBy(id="inputPassword")
	private WebElement PWTb;
	
	@FindBy(xpath="//button[text()='Sign In']")
	private WebElement loginBtn;

	public WebElement getUNTb() {
		return UNTb;
	}

	public WebElement getPWTb() {
		return PWTb;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	WebDriver driver;
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

}
