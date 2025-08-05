package implementationOfTestNG;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebdriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Homepage;

public class CreateCampaignWithMandatoryFields extends BaseClass{
	
	@Test
	public void createCampaignWithMandatoryFields() throws IOException, InterruptedException {

		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebdriverUtility wutil = new WebdriverUtility();

		// to read data from excel
		String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
		String target = eutil.toReadDataFromExcelFile("Campaign", 1, 3);

		// create campaign
		Homepage hp = new Homepage(driver);
		hp.getCreateCampaignbtn().click();

		Campaignpage cp = new Campaignpage(driver);
		cp.getCampaignNameTb().sendKeys(campname);
		WebElement size = cp.getTargetSizeTb();
		size.clear();
		size.sendKeys(target);
		Thread.sleep(2000);
		cp.getCreateCampaignSubmitBtn().click();

		// validation
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		if (msg.contains(campname)) {
			System.out.println("campaign created");
		} else {
			System.out.println("campaign not created");
		}
		cp.getClosemsg().click();

	}
}




