package testngpackage;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ConfigurationAnnot {
  @Test
  public void demo() {
	  System.out.println("TC Executed");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Login");

  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Logout");

  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Launch Browser");

  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Close Browser");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Pre Conditions");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Post Conditions");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("DB Connectivity open");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("DB Connectivity Close");
  }

}
