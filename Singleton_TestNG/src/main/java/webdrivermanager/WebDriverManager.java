package webdrivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverManager {
  private static WebDriverManager instance;
  WebDriver driver;
  private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

  private WebDriverManager() {}

  private void initDriver(String browser) {
    switch (browser) {
      case "chrome":
        tlDriver.set(new ChromeDriver());
        break;

      case "safari":
        tlDriver.set(new SafariDriver());
        break;
      default:
        throw new IllegalArgumentException("No Correct Browser Passed ");
    }
  }

  public static WebDriverManager getInstance(String Browser) {
    if (instance == null) {
      synchronized (WebDriverManager.class) {
        if (instance == null) instance = new WebDriverManager();
      }
    }
    if (tlDriver.get() == null) {
      instance.initDriver(Browser);
    }
    return instance;
  }

  public WebDriver getDriver() {
    return tlDriver.get();
  }

  public static void quitBrowser() {
    if (instance.getDriver() != null) {
      instance.getDriver().quit();
      tlDriver.remove();
    }
  }
}
