package pkg;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	String status;
	static String baseURL ="http://live.demoguru99.com/index.php/";

	@BeforeMethod
	void openBrowser() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Sumit\\Downloads\\Punit\\BrowserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	}


	@Test (priority = 1, enabled=false)
	void day1() throws InterruptedException {



		actualResult.delete(0, actualResult.length());
		status ="";

		actTitle = driver.findElement(By.xpath("//*[@class=\"page-title\"]/h2")).getText();
		assertEquals(actTitle.trim(), "THIS IS DEMO SITE FOR");
		System.out.println(actTitle);

		Thread.sleep(5000);
		ele = driver.findElement(By.linkText("MOBILE"));
		highlighter(ele);
		ele.click();


		actTitle = driver.getTitle();
		System.out.println(actTitle);

		try {
			assertEquals(actTitle.trim(),"Mobile");
		}catch(Exception e) {
			status="Fail";
			actualResult.append(e.getMessage().toString());
			System.out.println(status+"  ****  "+actualResult.toString());
		}

		ele  = driver.findElement(By.xpath("//select[@title=\"Sort By\"]"));

		Select sortBy = new Select(ele);
		sortBy.selectByVisibleText("Name");

		TakesScreenshot scrn = (TakesScreenshot) driver;
		File file = scrn.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(file, new File("C:\\Users\\Sumit\\eclipse-workspace-new\\ArtifactGuru\\Screenshots\\day1.jpg"));
		} catch (IOException e) {
			status="Fail";
			actualResult.append(e.getMessage().toString());
			System.out.println(status+"  ****  "+actualResult.toString());
		}

		actualResult.append("Everything is passed");
		System.out.println(status+"  ****  "+actualResult.toString());

	}







	@Test (priority = 2, dependsOnMethods = "day1", enabled = false)
	void day2() {



		actualResult.delete(0, actualResult.length());
		status ="PASS";
		driver.navigate().to(baseURL);

		driver.findElement(By.linkText("MOBILE")).click();

		ele  = driver.findElement(By.xpath("//*[@class=\"category-products\"]//li/a[@title=\"Xperia\"]//following-sibling::*//span[@class=\"price\"]"));

		String listPrice = ele.getText();
		System.out.println(listPrice);

		driver.findElement(By.xpath("//img[@alt='Xperia']")).click();
		ele = driver.findElement(By.cssSelector("span.h1"));
		System.out.println(ele.getText());


		String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();
		System.out.println(detailPrice);
		try {
			assertEquals(listPrice, detailPrice);
		}catch(Exception e) {
			status="Fail";
			actualResult.append(e.getMessage().toString());
			System.out.println(status+"  ****  "+actualResult.toString());
		}

		actualResult.append("list and detail price is same");
		System.out.println(status+"  ****  "+actualResult.toString());
	}






	@Test (priority = 3, enabled = false)
	void day3() {

			actualResult.delete(0, actualResult.length());
			status ="PASS";
			driver.navigate().to(baseURL);


			driver.findElement(By.linkText("MOBILE")).click();

			driver.findElement(By.xpath("//a[@title='Xperia']//following-sibling::div//button")).click();

			//driver.findElement(By.cssSelector("[title = 'Qty']")).sendKeys(Keys.CONTROL,"a");
			//driver.findElement(By.cssSelector("[title = 'Qty']")).sendKeys("555");
			//driver.findElement(By.cssSelector("[title = 'Qty']")).sendKeys(Keys.chord(Keys.CONTROL+"a"));


			driver.findElement(By.cssSelector("[title = 'Qty']")).clear();
			driver.findElement(By.cssSelector("[title = 'Qty']")).sendKeys("1500");

			System.out.println("Clear");

			driver.findElement(By.cssSelector("[title='Update']")).click();

			ele = driver.findElement(By.xpath("//*[contains(text(),'maximum')]"));
			String actErrorMsg = ele.getText();

			System.out.println(actErrorMsg);
			try {
				assertEquals(actErrorMsg.trim(), "* The maximum quantity allowed for purchase is 500.");
			}catch(Exception e) {
				status="Fail";
				actualResult.append(e.getMessage().toString());
				System.out.println(status+"  ****  "+actualResult.toString());

			}
			driver.findElement(By.xpath("//span[text()='Empty Cart']")).click();

			ele = driver.findElement(By.className("page-title"));
			String emptyMsg = ele.getText();
			System.out.println(emptyMsg);
			try {
				assertEquals(emptyMsg.trim(), "SHOPPING CART IS EMPTY");
			}catch(Exception e) {
				status="Fail";
				actualResult.append(e.getMessage().toString());
				System.out.println(status+"  ****  "+actualResult.toString());

			}

			actualResult.append("Cart is empty");
			System.out.println(status+"  ****  "+actualResult.toString());

		}


	@Test (priority = 4, enabled = false)
	void day4() throws IOException, InterruptedException {

			actualResult.delete(0, actualResult.length());
			status ="PASS";
			driver.navigate().to(baseURL);


			driver.findElement(By.linkText("MOBILE")).click();

			driver.findElement(By.xpath("//a[text()='Sony Xperia']/..//following-sibling::div//a[text()='Add to Compare']")).click();
			driver.findElement(By.xpath("//a[text()='IPhone']/..//following-sibling::div//a[text()='Add to Compare']")).click();
			
			driver.findElement(By.xpath("//button//*[contains(text(),'Compare')]")).click();

		    
			
		    for(String handle:  driver.getWindowHandles()) {
		    	System.out.println(handle);
		    	driver.switchTo().window(handle);
		    }

		    Thread.sleep(5000);
			
		   TakesScreenshot scn = (TakesScreenshot)driver;
		   File file = scn.getScreenshotAs(OutputType.FILE);
		   FileHandler.copy(file, new File("C:\\Users\\Sumit\\eclipse-workspace-new\\ArtifactGuru\\Screenshots\\day4.jpg"));
		    
		   
		   driver.findElement(By.xpath("//*[text()='Close Window']")).click();
		   
		   
		    

		}



	@Test (priority = 5, enabled = false)
	void day5() throws IOException, InterruptedException {

			actualResult.delete(0, actualResult.length());
			status ="PASS";
			driver.navigate().to(baseURL);


			driver.findElement(By.linkText("MOBILE")).click();

			driver.findElement(By.xpath("//a[text()='Sony Xperia']/..//following-sibling::div//a[text()='Add to Compare']")).click();
			driver.findElement(By.xpath("//a[text()='IPhone']/..//following-sibling::div//a[text()='Add to Compare']")).click();
			
			driver.findElement(By.xpath("//button//*[contains(text(),'Compare')]")).click();

		    
			
		    for(String handle:  driver.getWindowHandles()) {
		    	System.out.println(handle);
		    	driver.switchTo().window(handle);
		    }

		    Thread.sleep(5000);
			
		   TakesScreenshot scn = (TakesScreenshot)driver;
		   File file = scn.getScreenshotAs(OutputType.FILE);
		   FileHandler.copy(file, new File("C:\\Users\\Sumit\\eclipse-workspace-new\\ArtifactGuru\\Screenshots\\day4.jpg"));
		    
		   
		   driver.findElement(By.xpath("//*[text()='Close Window']")).click();
		   
		   
		    

		}


		void highlighter(WebElement ele) throws InterruptedException {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;')", ele);
			Thread.sleep(500);

		}


		@AfterMethod
		void quit() 
		{
			driver.quit();
		}




	}
