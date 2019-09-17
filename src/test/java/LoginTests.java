import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTests {

    WebDriver driver;
    String url = "http://automationpractice.com/index.php";
    String emailValue = "ahmed1@test.com";
    String passwordValue = "12345";

    By loginBtnLocator = By.className("login");
    By loginEmailLocator = By.id("email");
    By loginPasswordLocator = By.id("passwd");
    By loginSubmitBtnLocator = By.id("SubmitLogin");
    By shoppingCartLocator = By.className("shopping_cart");
    By loginFormLocator = By.id("login_form");

    WebDriverWait wait;
    int waitTimeOut =  60;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allow-running-insecure-content"); //Load unsafe scripts
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get(url);
        wait = new WebDriverWait(driver,waitTimeOut);
    }

    @Test(priority = 1)
    public void goToLoginForm(){
        SoftAssert softAssert = new SoftAssert();
        wait.until(ExpectedConditions.elementToBeClickable(loginBtnLocator));
        WebElement loginBtn = driver.findElement(loginBtnLocator);
        loginBtn.click();
        softAssert.assertTrue(driver.findElement(loginFormLocator).isDisplayed(),"Login button don't direct user to login form");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void addLoginCredentials(){
        SoftAssert softAssert = new SoftAssert();
        wait.until(ExpectedConditions.presenceOfElementLocated(loginEmailLocator));
        WebElement email = driver.findElement(loginEmailLocator);
        email.sendKeys(emailValue);
        WebElement password = driver.findElement(loginPasswordLocator);
        password.sendKeys(passwordValue);
        WebElement loginBtn = driver.findElement(loginSubmitBtnLocator);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
        softAssert.assertTrue(driver.findElement(shoppingCartLocator).isDisplayed(),"Cart is not displayed after login");
        softAssert.assertAll();
    }

}
