package objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	WebDriver driver;
	public Homepage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Campaigns'")
	private WebElement CampaignsLnk;
	
	@FindBy(linkText="Contacts")
	private WebElement ContactsLnk;
	
	@FindBy(linkText="Products")
	private WebElement ProductsLnk;
	
	@FindBy(xpath="//span[text()='Create Campaign']")
	private WebElement CreateCampaignBtn;
	
	@FindBy(xpath="//div[@class='user-icon']")
	private WebElement UserIcon;
	
	@FindBy(xpath="//div[@class='dropdown-item logout']")
	private WebElement LogoutBtn;

	public WebElement getCampaignslnk() {
		return CampaignsLnk;
	}

	public WebElement getContactslnk() {
		return ContactsLnk;
	}

	public WebElement getProductslnk() {
		return ProductsLnk;
	}

	public WebElement getCreateCampaignbtn() {
		return CreateCampaignBtn;
	}

	public WebElement getUserIcon() {
		return UserIcon;
	}

	public WebElement getLogoutbtn() {
		return LogoutBtn;
	}
	
	
	
	
	
	

}
