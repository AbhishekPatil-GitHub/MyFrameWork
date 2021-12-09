package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import main.java.utility.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;



    @BeforeSuite
    public void beforeSuite(){
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+File.separator+"Report"+File.separator+"AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Execution Result");
        htmlReporter.config().setTheme(Theme.DARK);
        extent=new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester","Abhishek");
    }


    @BeforeTest
    @Parameters(value = {"browsername"})
    public void beforeMethod(String browsername, Method testMethod){
        logger=extent.createTest(testMethod.getName());
        setupDriver(browsername);
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterTest
    public void afterMethod(ITestResult result){
        if (result.getStatus()==ITestResult.SUCCESS){
            String methodName=result.getMethod().getMethodName();
            String logText="Test Case : "+methodName+" PASSED";
            Markup m= MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        }else if (result.getStatus()==ITestResult.FAILURE){
            String methodName=result.getMethod().getMethodName();
            String logText="Test Case : "+methodName+" FAILED";
            Markup m= MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, m);

        }
        driver.quit();

    }

    @AfterSuite
    public void afterTest(){
        extent.flush();

    }

    public void setupDriver(String browserName){
        if (browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ File.separator+"Drivers"+File.separator+"chromedriver.exe");
            driver=new ChromeDriver();

        }else if (browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ File.separator+"Drivers"+File.separator+"geckodriver");
            driver=new FirefoxDriver();
        }else driver=null;


    }


}
