package testcases;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.BaseClass;
import utilities.ExcelDataSupllier;

public class TestCases extends BaseClass {
	// For Data driven test
	@Test (testName = "logininEcommerce", dataProvider = "logindata", dataProviderClass = ExcelDataSupllier.class)
	public void logininEcommerce(String useremail, String userpass)throws Exception {
		
		driver.get(configprop.getProperty("url"));
		driver.findElement(By.linkText(locprop.getProperty("btn_login"))).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement elemente = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("input_email"))));
		elemente.sendKeys(useremail);
		
		WebElement elementp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("input_pass"))));
		elementp.sendKeys(userpass);     
		
		driver.findElement(By.xpath(locprop.getProperty("btn_signin "))).click();
		
		Assert.assertTrue(driver.findElement(By.xpath(locprop.getProperty("success"))).isDisplayed());
		
		
	}
	
	@Test(testName = "buyProduct")
	public void buyProduct()throws Exception {
		driver.get(configprop.getProperty("url"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement searchpro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("search"))));
		searchpro.sendKeys("pet Food",Keys.ENTER);
		
		WebElement selecthpro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("select_product"))));
		selecthpro.click();
		
		WebElement addpro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locprop.getProperty("add_cart"))));
        selecthpro.click();
        
		WebElement buypro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("buy_pro"))));
        buypro.click();
        
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("address"))));
        address.click();
		
        
        //add address
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locprop.getProperty("fullname"))));
        name.sendKeys("Masoma");
        
        WebElement number = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locprop.getProperty("phone"))));
        number.sendKeys("01995677844");
        
       
        WebElement district = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("district"))));
        district.click();
        Select dist = new Select(district);
        dist.selectByVisibleText("Barguna");
        
        WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("city"))));
        city.click();
        Select buyercity= new Select(city);
        buyercity.selectByVisibleText("Bamna");
        
        
        WebElement userarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("area"))));
        userarea.click();
        Select buyerarea= new Select(userarea);
        buyercity.selectByVisibleText("colony");
        
        WebElement userdetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("detaisl"))));
        userdetails.sendKeys("Dhaka, Bangladesh");
        
        WebElement effectdelivery = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("delevery"))));
        effectdelivery.click();
        
        WebElement ddadd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("ddaddress"))));
        ddadd.click();
        
        WebElement submitinfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("submit"))));
        submitinfo.click();
        
        WebElement validation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locprop.getProperty("infovalidation"))));
        String message = validation.getText();
        String expectedPartial =  "Order summary";
        
        if (message.contains(expectedPartial)) {
        	System.out.println("Order successful");
        } else {
			System.out.println("Order is not successful");
		}
    
	}
	
}
