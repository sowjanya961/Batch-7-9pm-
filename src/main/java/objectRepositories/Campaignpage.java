package objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Campaignpage {
	
	WebDriver driver;
	public Campaignpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="campaignName")
	private WebElement campaignNameTb;
	
	@FindBy(name="campaignStatus")
	private WebElement campaignStatusTb;
	
	@FindBy(name="targetSize")
	private WebElement targetSizeTb;
	
	@FindBy(name="expectedCloseDate")
	private WebElement dateTb;
	
	@FindBy (xpath="//button[text()='Create Campaign']")
	private WebElement createCampaignSubmitBtn;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement toastmsg;
	
	@FindBy(xpath="//button[@aria-label='close']")
	private WebElement closemsg;

	public WebElement getCampaignNameTb() {
		return campaignNameTb;
	}

	public WebElement getCampaignStatusTb() {
		return campaignStatusTb;
	}

	public WebElement getTargetSizeTb() {
		return targetSizeTb;
	}

	public WebElement getDateTb() {
		return dateTb;
	}

	public WebElement getCreateCampaignSubmitBtn() {
		return createCampaignSubmitBtn;
	}

	public WebElement getToastmsg() {
		return toastmsg;
	}

	public WebElement getClosemsg() {
		return closemsg;
	}
	
}
