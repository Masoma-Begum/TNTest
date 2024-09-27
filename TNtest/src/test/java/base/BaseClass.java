package base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {

	public static WebDriver driver;
	public static Properties configprop = new Properties();
	public static Properties locprop = new Properties();
	public static FileReader con;
	public static FileReader loca;
	//public static String captchSubFolder;
	//public static String captureImage;

	@BeforeTest
	public void setup() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// "user.dir" is locate any direction of file in pc
		con = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfile\\config.properties");
		loca = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfile\\locators.properties");

		configprop.load(con);
		locprop.load(loca);
	}
	/*  @AfterMethod
	public void manageCapture(ITestResult testResult) throws IOException {
		if(testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getTestContext().getName()+"_"+testResult.getMethod().getMethodName());
			captureScreen(testResult.getTestContext().getName()+"_"+testResult.getMethod().getMethodName());
		}
	}*/

	@AfterTest
	public void teardown() throws Exception {
		Thread.sleep(5000);
		driver.quit();

	}

	/*  public static void captureScreen(String fileName) throws IOException{

	//If image is not existing according to this time an image will be create with time
	  if(captureImage == null) {
		  LocalTime time = LocalTime.now();
		  DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
		  captureImage = time.format(timeFormatter);	  
	  }
    //A folder will be create according to current date
	  LocalDate date = LocalDate.now();
	  DateTimeFormatter folderFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy/");
	  String folder = date.format(folderFormatter);

		File sourceFile =  ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
	    File destFile = new File("./Screenshots/"+folder+fileName+captureImage+".jpg");
		FileUtils.copyFile(sourceFile,destFile);


  }*/
}
