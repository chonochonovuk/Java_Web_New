package bg.softunit.chrometest;

import java.io.File;
import java.net.URL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeTest {

  private static final String CHROMEDRIVER_FILE = "chromedriver";
  protected static WebDriver driver;

  @BeforeAll
  public static void setUp() {
    String driverFile = findFile();
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    ChromeDriverService service = new ChromeDriverService.Builder()
        .usingDriverExecutable(new File(driverFile))
        .build();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
    options.addArguments("start-maximized"); // open Browser in maximized mode
    options.addArguments("--disable-extensions"); // disabling extensions
    options.merge(capabilities);
    driver = new ChromeDriver(service, options);
  }

  @Test
  public void test() {
    // navigate to localhost
    driver.get("http://localhost:8080");

    //fill username and password
    WebElement usernameInput = driver.findElement(By.name("username"));
    WebElement passwordInput = driver.findElement(By.name("password"));

    usernameInput.sendKeys("user@example.com");
    passwordInput.sendKeys("topsecret");

    passwordInput.submit();

    WebElement titleOnHomePage = driver.findElement(By.tagName("h1"));
    Assertions.assertEquals("Welcome to Tabula, user@example.com!",
        titleOnHomePage.getText());
  }

  private static String findFile() {
    ClassLoader classLoader = ChromeTest.class.getClassLoader();
    URL url = classLoader.getResource(CHROMEDRIVER_FILE);
    return url.getFile();
  }

  @AfterAll
  public static void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
