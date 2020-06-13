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
	static String firstName = "BERRY"+(int)System.currentTimeMillis()/1000000;    // Firstname and Lastname will be placed    
	static String lastName = "BERRYTWO";
	static String email = "P"+System.currentTimeMillis()+"@tpg.com.au";
	static String password ="mar8mar";

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
			FileHandler.copy(file, new File("C:\\Users\\Sumit\\eclipse-workspace-new\\ArtifactGuru\\Screenshots\\day1.png"));
		} catch (IOException e) {
			status="Fail";
			actualResult.append(e.getMessage().toString());
			System.out.println(status+"  ****  "+actualResult.toString());
		}

		actualResult.append("Everything is passed");
		System.out.println(status+"  ****  "+actualResult.toString());

	}







	@Test (priority = 2, enabled = false)
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


		actualResult.append("Cart is empty");
		System.out.println(status+"  ****  "+actualResult.toString());

	}



	@Test (priority = 5, enabled = true)
	void day5() throws IOException, InterruptedException {

		// 1. Go to http://live.demoguru99.com
		driver.get(baseURL); 

		// 2. Click on my account link
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		Thread.sleep(2000);


		// 3a. Click Create an Account link 
		driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
		Thread.sleep(2000);


		// 3b. and fill New User information 
		driver.findElement(By.id("firstname")).clear();	   
		driver.findElement(By.id("firstname")).sendKeys(firstName); 
		driver.findElement(By.id("lastname")).clear();	    
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).clear();
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("mar8mar");
		driver.findElement(By.id("confirmation")).clear();
		driver.findElement(By.id("confirmation")).sendKeys("mar8mar");

		// 4. Click Register
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Thread.sleep(2000);

		// 5. Verify Registration is done. Expected account registration done.
		String vWelcome = ("WELCOME, " + firstName + " " + lastName + "!");
		String tWelcome = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[1]/div/p")).getText();
		System.out.println("tWelcome = "+tWelcome);

		try {	    	
			assertEquals(tWelcome, vWelcome);
		} catch (Exception e) {
			e.printStackTrace();	    	
		}	

		// 6. Go to TV menu	    
		driver.findElement(By.xpath(".//*[@id='nav']/ol/li[2]/a")).click();
		Thread.sleep(2000);


		// 7. Add product in your wish list - use product - LG LCD	    
		driver.findElement(By.xpath("//li/a[@class='link-wishlist']")).click();

		Thread.sleep(2000);


		// 8. Click SHARE WISHLIST 
		driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

		Thread.sleep(2000);


		// 9. In next page enter Email and a message and click SHARE WISHLIST
		driver.findElement(By.id("email_address")).clear();
		driver.findElement(By.id("email_address")).sendKeys("support@guru99.com");
		driver.findElement(By.id("message")).clear();
		driver.findElement(By.id("message")).sendKeys("Hey Mary!! this LCD TV looks ok, check it out !!.. cheers .. Berry");

		driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
		// the above click will still be in the same page as prior page, so no need take another handle to another window	    

		Thread.sleep(2000);

		// 10. Check wishlist is shared. Expected wishlist shared successfully. 
		String vWishList = "Your Wishlist has been shared.";
		String wishList = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
		System.out.println("wishList = "+wishList);
		try {	    	
			assertEquals(vWishList, wishList);
		} catch (Exception e) {
			e.printStackTrace();	    	
		}	

		Thread.sleep(2000);

	}

	@Test (priority = 6, enabled = true)
	void day6() throws InterruptedException {



		actualResult.delete(0, actualResult.length());
		status ="PASS";
		System.out.println(email+" "+password);
		driver.get(baseURL);


		driver.findElement(By.linkText("MY ACCOUNT")).click();
		Thread.sleep(2000);

		driver.findElement(By.name("login[username]")).sendKeys(email);
		driver.findElement(By.name("login[password]")).sendKeys(password);		
		driver.findElement(By.cssSelector("button#send2")).click();

		driver.findElement(By.xpath("//a[text()='My Wishlist']")).click();

		driver.findElement(By.xpath("//span[text()='Add to Cart']")).click();

		driver.findElement(By.xpath("//span[text()='Proceed to Checkout']")).click();

		Thread.sleep(5000);
		new Select (driver.findElement(By.name("billing[country_id]"))).selectByVisibleText("United States");

		new Select(driver.findElement(By.id("billing:region_id"))).selectByVisibleText("New York");;

        driver.findElement(By.id("billing:postcode")).sendKeys("542896");


	}


	void highlighter(WebElement ele) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;')", ele);
		Thread.sleep(500);

	}


	@AfterMethod
	void quit() 
	{
		//driver.quit();
	}




}
