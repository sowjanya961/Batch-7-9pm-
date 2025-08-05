package testngpackage;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertion {

	@Test
	public void demo1() {
		String expTitle = "Facebook – log in or sign up";
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");

		String actTitle = driver.getTitle();
		// HardAssert
		Assert.assertEquals(expTitle, actTitle);
		System.out.println("abc");
		System.out.println("xyz");
	}

	@Test
	public void demo2() {
		String expTitle = "Facebook – log in or sign up";
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		String actTitle = driver.getTitle();
		// SoftAssert
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expTitle, actTitle);
		System.out.println("abc");
		System.out.println("xyz");
		soft.assertAll();

	}

}
