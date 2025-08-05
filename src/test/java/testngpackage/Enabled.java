package testngpackage;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Enabled {
	
	@Test
	public void sum() {
		Reporter.log("sum",true);
	}
   
	@Test(enabled = false)
	public void prod() {
		Reporter.log("prod",true);
	}
	@Test(invocationCount = 0)
	public void div() {
		Reporter.log("div",true);
	}
	@Test
	public void diff() {
		Reporter.log("diff",true);
	}
}
