import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import TrackoGenericUtility.GenericUtils;

public class TrackoDemoAssignmentSignup {
	public static WebDriver driver;
	public static WebDriverWait wait;
	GenericUtils genericUtils=new GenericUtils();
  
	/**
	 *  Launch Application using driver conf
	 */
    public void launchTracko() {
	//	initialiZeDirver();
        driver.get("https://dev.tracko.co.in/");
     
        	Assert.assertEquals(driver.getTitle().contains("Tracko"), true);
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='navbar-collapse']/ul/li[5]/a")));
            System.out.println("Application SuccessFully Launched");
    }
    
    /**
     * Positive scenario for Signup Tracko
     */
  
	@Test(priority = 1)
	public void signUpForTrackoPositive() {
		try {
			launchTracko();
			driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[5]/a")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("html/body/div[1]/section/div/div/div/div/h2")));
			WebElement name = driver.findElement(By.xpath(".//*[@id='name']"));
			name.sendKeys("saurabh kishor dixit");
			WebElement email = driver.findElement(By.xpath(".//*[@id='email']"));
			email.sendKeys("try@gmail.com");
			WebElement mobileNo = driver.findElement(By.xpath(".//*[@id='mobile']"));
			mobileNo.sendKeys("1121154610");
			driver.findElement(By.xpath(".//*[@id='sub_btn_signup']")).click();

			Boolean isElementPresent = genericUtils.isElementPresent(driver, By.xpath(".//*[@id='otp']"));
			if (isElementPresent) {
				String singnupId = driver.getWindowHandle();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='otp']")));
				WebElement otp = driver.findElement(By.xpath(".//*[@id='otp']"));
				otp.sendKeys("123456");
				driver.findElement(By.xpath(".//*[@id='sub_btn_verify']")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("html/body/div[3]/div[2]")).sendKeys(Keys.ENTER);
				driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Saurabh@123");
				driver.findElement(By.xpath(".//*[@id='repassword']")).sendKeys("Saurabh@123");
				driver.findElement(By.xpath(".//*[@id='sub_btn_setpass']")).click();
				driver.quit();
			}
		} catch (Exception e) {
			 throw new AssertionError("failure", e);
		}
	}
	
	
	/**
	 * Signup With already registered user scenario
	 */
	@Test(priority = 2)
	public void doSignUpForTrackoWithAlreadyRegisterUser() {
		try {
			initialiZeDriver();
			launchTracko();
			
			driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[5]/a")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("html/body/div[1]/section/div/div/div/div/h2")));
			WebElement name = driver.findElement(By.xpath(".//*[@id='name']"));
			name.sendKeys("saurabh kishor dixit");
			WebElement email = driver.findElement(By.xpath(".//*[@id='email']"));
			email.sendKeys("try@gmail.com");
			WebElement mobileNo = driver.findElement(By.xpath(".//*[@id='mobile']"));
			mobileNo.sendKeys("1121154610");
			driver.findElement(By.xpath(".//*[@id='sub_btn_signup']")).click();
			driver.quit();
			}
		 catch (Exception e) {
			 throw new AssertionError("failure", e);
		}
	}
	
	
	/**
	 * Negative Scenario without Mo no
	 */
	@Test(priority = 3)
	public void doSignUpForTrackoWithoutMobileNumber() {
		try {
			initialiZeDriver();
			launchTracko();
			
			driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[5]/a")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("html/body/div[1]/section/div/div/div/div/h2")));
			WebElement name = driver.findElement(By.xpath(".//*[@id='name']"));
			name.sendKeys("saurabh kishor dixit");
			WebElement email = driver.findElement(By.xpath(".//*[@id='email']"));
			email.sendKeys("sau@gmail.com");
			driver.findElement(By.xpath(".//*[@id='sub_btn_signup']")).click();
			
			String mobileNoMissErr = driver.findElement(By.xpath(".//*[@id='mobile-error']")).getText();
			Assert.assertEquals(mobileNoMissErr.contains("Please provide your mobile no."), true);
			driver.quit();
			}
		 catch (Exception e) {
			 throw new AssertionError("failure", e);
		}
	}
	
	/**
	 * Negative Scenario without Email Id
	 */
	
	@Test(priority = 4)
	public void doSignUpForTrackoWithoutEmailId() {
		try {
			initialiZeDriver();
			launchTracko();

			driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[5]/a")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("html/body/div[1]/section/div/div/div/div/h2")));
			WebElement name = driver.findElement(By.xpath(".//*[@id='name']"));
			name.sendKeys("saurabh kishor dixit");
			WebElement mobileNo = driver.findElement(By.xpath(".//*[@id='mobile']"));
			mobileNo.sendKeys("3113456780");
			driver.findElement(By.xpath(".//*[@id='sub_btn_signup']")).click();

			String emailMissingErr = driver.findElement(By.xpath(".//*[@id='email-error']")).getText();
			Assert.assertEquals(emailMissingErr.contains("Please provide your email id."), true);
			driver.quit();
		} catch (Exception e) {
			 throw new AssertionError("failure", e);
		}
	}
	
	
	/**
	 * Negative Scenario without Name
	 */
	@Test(priority = 5)
	public void doSignUpForTrackoWithoutName() {
		try {
			initialiZeDriver();
			launchTracko();

			driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[5]/a")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("html/body/div[1]/section/div/div/div/div/h2")));
			WebElement email = driver.findElement(By.xpath(".//*[@id='email']"));
			email.sendKeys("sau@gmail.com");
			WebElement mobileNo = driver.findElement(By.xpath(".//*[@id='mobile']"));
			mobileNo.sendKeys("3113456780");
			driver.findElement(By.xpath(".//*[@id='sub_btn_signup']")).click();

			String nameMissingErr = driver.findElement(By.xpath(".//*[@id='name-error']")).getText();
			Assert.assertEquals(nameMissingErr.contains("Please provide your name."), true);
			driver.quit();
		} catch (Exception e) {
			 throw new AssertionError("failure", e);
		}
	}
	
	@AfterTest
	public void cleanup(){
	driver.quit();
	}
	
  
    @BeforeClass
    public void initialiZeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 60);
    }
    
}