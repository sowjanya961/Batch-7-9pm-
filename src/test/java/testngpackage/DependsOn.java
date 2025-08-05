package testngpackage;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class DependsOn {

	@Test()
	public void addCart() {
		Reporter.log("added",true);
	}
	

	@Test(dependsOnMethods ="addCart")
	public void editCart() {
		Reporter.log("editted",true);
	}
	

	@Test(dependsOnMethods ={"addCart","editCart"})
	public void deleteCart() {
		Reporter.log("deleted",true);
	}
}
