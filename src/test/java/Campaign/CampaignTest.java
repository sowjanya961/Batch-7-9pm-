package Campaign;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebdriverUtility;
import objectRepositories.Campaignpage;
import objectRepositories.Homepage;
@Listeners(ListenerUtility.ListenerImplementation.class)
public class CampaignTest extends BaseClass {
	@Test(groups = "smoke")
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
		Assert.assertTrue(msg.contains(campname));
		cp.getClosemsg().click();
	}

	@Test(groups = "regression")
	public void createCampaignWithExpDate() throws Throwable, IOException {
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebdriverUtility wutil = new WebdriverUtility();

		// to read data from excel
		String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
		String target = eutil.toReadDataFromExcelFile("Campaign", 1, 3);

		// date after 30 days
		String daterequired = jutil.togetRequiredDate(30);

		// create campaign
		Homepage hp = new Homepage(driver);
		hp.getCreateCampaignbtn().click();

		Campaignpage cp = new Campaignpage(driver);
		cp.getCampaignNameTb().sendKeys(campname);
		cp.getCampaignStatusTb().sendKeys("pass");
		WebElement size = cp.getTargetSizeTb();
		size.clear();
		size.sendKeys(target);
		Thread.sleep(2000);

		WebElement expclosedate = cp.getDateTb();
		wutil.passInput(driver, expclosedate, daterequired);

		cp.getCreateCampaignSubmitBtn().click();

		// validation
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));//to get ss,we need to fail instead of contains use equals
		cp.getClosemsg().click();
	}

	@Test(groups = "smoke")
	public void createCampaignWithStatus() throws IOException {
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
		cp.getCampaignStatusTb().sendKeys("pass");
		WebElement size = cp.getTargetSizeTb();
		size.clear();
		size.sendKeys(target);
		cp.getCreateCampaignSubmitBtn().click();

		// validation
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));
		cp.getClosemsg().click();
	}
}
