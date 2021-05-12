package TestCases;

import Pages.loginPage;
import main.java.utility.Constants;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.XMLConstants;
import test.BaseTest;


public class loginTestCase extends BaseTest{
    loginPage loginpg= PageFactory.initElements(driver, loginPage.class);

    @Test
    public void loginTC(){
        //Entering Username
//        loginpg.enterUserName(Constants.username);
//        //Clicking Continue Button
//        loginpg.clickContinueBtn();
        logger.info("Entering the USerName");
        Assert.assertTrue(false);

    }
    @Test
    public void testMethod2(){
        logger.info("Executing test Method 2");
        System.out.println("TestMethod2");
    }

}
