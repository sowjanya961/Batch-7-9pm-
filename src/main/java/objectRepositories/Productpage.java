package objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Productpage {
	
	WebDriver driver;
   public Productpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
			}
   
   @FindBy(xpath="//span[text()='Add Product']")
   private WebElement addProductBtn;
   
   @FindBy(name="productName")
   private WebElement productNameTb;
   
   @FindBy(name="productCategory")
   private WebElement categoryDd;
   
   @FindBy(name="quantity")
   private WebElement quantityTb;
   
   @FindBy(name="price")
   private WebElement priceTb;
   
   @FindBy(name="vendorId")
   private WebElement vendorDd;
   
   @FindBy(xpath="//button[text()='Add']")
   private WebElement addProductSubmitBtn;
   
public WebElement getAddProductBtn() {
	return addProductBtn;
}

public WebElement getProductName() {
	return productNameTb;
}

public WebElement getCategoryDd() {
	return categoryDd;
}

public WebElement getQuantityTb() {
	return quantityTb;
}

public WebElement getPriceTb() {
	return priceTb;
}

public WebElement getVendorDd() {
	return vendorDd;
}

public WebElement getAddProductSubmitBtn() {
	return addProductSubmitBtn;
}
   

}
