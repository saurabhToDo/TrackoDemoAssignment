import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import TrackoGenericUtility.GenericUtils;

public class TrackoDemoAssignmentLogin {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	GenericUtils genericUtils=new GenericUtils();
	
	public void launchTracko() {
		driver.get("https://dev.tracko.co.in/");
		Assert.assertEquals(driver.getTitle().contains("Tracko"), true);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='navbar-collapse']/ul/li[5]/a")));
		System.out.println("Application SuccessFully Launched");
	}
	    
	 
	@Test(priority = 1)
	public void loginForTrackoPostive() {
		launchTracko();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='navbar-collapse']/ul/li[4]/a")));
		driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[4]/a")).click();
		Assert.assertEquals(driver.getTitle().contains("Log In"), true);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("html/body/div[1]/section/div/div/div/div/h2")));
			driver.findElement(By.xpath(".//*[@id='login-mobile']")).sendKeys("1121154610");
			driver.findElement(By.xpath(".//*[@id='login-password']")).sendKeys("Saurabh@123");
			driver.findElement(By.xpath(".//*[@id='sub_btn']")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(".//*[@id='navbar-collapse']/ul/li[2]/a")));
			Assert.assertEquals(driver.getTitle().contains("Tracko Products"), true);
				System.out.println("Login SuccessFully");
				driver.quit();

		}

	
	@Test(priority = 2)
	public void loginForTrackoWithoutUsername() {
		try {
			initialiZeDriver();
			launchTracko();
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='navbar-collapse']/ul/li[4]/a")));
			driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[4]/a")).click();
			Assert.assertEquals(driver.getTitle().contains("Log In"), true);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("html/body/div[1]/section/div/div/div/div/h2")));
				driver.findElement(By.xpath(".//*[@id='login-password']")).sendKeys("Saurabh@123");
				driver.findElement(By.xpath(".//*[@id='sub_btn']")).click();
				driver.quit();

		} catch (Exception e) {
			System.out.println(e);
			driver.quit();
		}
	}
	
	
	@Test(priority = 3)
	public void loginForTrackoWithoutPassword() {
		try {
			initialiZeDriver();
			launchTracko();
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='navbar-collapse']/ul/li[4]/a")));
			driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[4]/a")).click();
			Assert.assertEquals(driver.getTitle().contains("Log In"), true);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("html/body/div[1]/section/div/div/div/div/h2")));
			driver.findElement(By.xpath(".//*[@id='login-mobile']")).sendKeys("5218999610");
			driver.findElement(By.xpath("html/body/div[3]/div[2]/button[1]")).sendKeys(Keys.ENTER);
			driver.quit();
		} catch (Exception e) {
			System.out.println(e);
			driver.quit();
		}

	}
	
	/**
	 * Negative Test Case for Login(SQL Injection attacked )
	 */
	@Test(priority = 4)
	public void loginForTrackoWithSQLInjectionAttack() {
		try {
			initialiZeDriver();
			launchTracko();
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='navbar-collapse']/ul/li[4]/a")));
			driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[4]/a")).click();
			Assert.assertEquals(driver.getTitle().contains("Log In"), true);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("html/body/div[1]/section/div/div/div/div/h2")));
			driver.findElement(By.xpath(".//*[@id='login-mobile']")).sendKeys("1129859610");
			driver.findElement(By.xpath(".//*[@id='login-password']")).sendKeys("admin1' or '1'='1");
			driver.findElement(By.xpath("html/body/div[3]/div[2]/button[1]")).sendKeys(Keys.ENTER);
			driver.quit();

		} catch (Exception e) {
			System.out.println(e);
			driver.quit();
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

