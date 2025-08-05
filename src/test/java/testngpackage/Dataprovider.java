package testngpackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider {

	@Test(dataProvider = "loginDetails")
	public void login(String firstname,String lastname) {
		System.out.println(firstname+"="+lastname);
	}
	
	@DataProvider
	public Object[][] loginDetails() {
		Object[][] objarr = new Object[4][2];
		objarr[0][0]="sam";
		objarr[0][1]="ram";
		objarr[1][0]="john";
		objarr[1][1]="son";
		objarr[2][0]="peter";
		objarr[2][1]="parker";
		objarr[3][0]="will";
		objarr[3][1]="smith";
		
         return objarr;
		
	}
}
