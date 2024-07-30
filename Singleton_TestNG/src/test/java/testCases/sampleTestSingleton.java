package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdrivermanager.WebDriverManager;

public class sampleTestSingleton {
  static WebDriver driver;
  public String bro;

  @Parameters("Browser")
  @BeforeClass
  public void setDriver(String Browser) {
    driver = WebDriverManager.getInstance(Browser).getDriver();
    bro=Browser;
  }

  @Test
  public void testCase1() {
    driver.get("https://www.google.com");
    Reporter.log("Current Hashcode of driver= " + driver.hashCode());
    System.out.println("Current Hashcode of driver "+bro +" = " + driver.hashCode());
      try {
          Thread.sleep(5000);
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
  }
  @AfterMethod
  public void end(){
     WebDriverManager.quitBrowser();
  }
}
