package testngpackage;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo {
	@Test
	public void a11() {
		Reporter.log("a11 executed", true);
	}
	
	@Test
	public void a23() {
		Reporter.log("a23 executed", true);
    }
	
	@Test
	public void a16() {
		Reporter.log("a16 executed", true);
    }
	
	@Test
	public void b32() {
		Reporter.log("b32 executed", true);
	}

	@Test
	public void b24() {
		Reporter.log("b24 executed", true);

	}
	
	public void b17() {
		Reporter.log("b17 executed", false);

	}
}
