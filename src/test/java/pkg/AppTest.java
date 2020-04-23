package pkg;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	
	static WebDriver driver=null;
	StringBuilder actualResult = new StringBuilder();
	String actTitle="";
	WebElement ele =null;
	String status ="Pass";
	
   @BeforeMethod
	void openBrowser() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Sumit\\Downloads\\Punit\\BrowserDrivers\\geckodriver.exe");
	    driver = new FirefoxDriver();
		
		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	
	@Test
	void day1() {
		
		try {
			
		 actTitle = driver.findElement(By.xpath("//*[@class=\"page-title\"]/h2")).getText();
		 assertEquals(actTitle.trim(), "THIS IS DEMO SITE FOR");
		 
		 Thread.sleep(5000);
		 driver.findElement(By.linkText("MOBILE")).click();
		 
		 actTitle = driver.getTitle();
		 assertEquals(actTitle.trim(),"Mobile");
			
		 ele  = driver.findElement(By.xpath("//select[@title=\"Sort By\"]"));
		 
		 Select sortBy = new Select(ele);
		 sortBy.selectByVisibleText("Name");
		 
		 TakesScreenshot scrn = (TakesScreenshot) driver;
		 File file = scrn.getScreenshotAs(OutputType.FILE);
		 FileHandler.copy(file, new File("C:\\Users\\Sumit\\eclipse-workspace-new\\ArtifactGuru\\Screenshots\\day1.jpg"));
		 
		 actualResult.append("Everything is passed");
		 System.out.println(status+"  ****  "+actualResult.toString());
		}
		
		catch (Exception e) {
			status="Fail";
			actualResult.append(e.getMessage().toString());
			System.out.println(status+"  ****  "+actualResult.toString());
		}
		
		
	}
	
	@AfterMethod
	void quit() 
	{
		//driver.quit();
	}
	
	
	
    
}
