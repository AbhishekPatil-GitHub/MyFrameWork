package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.BaseTest;

public class loginPage extends BaseTest{
    @FindBy(xpath = "//input[@type='text']")
    WebElement inputTextBox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement continueButton;

    public void enterUserName(String username){
        inputTextBox.sendKeys(username);
    }

    public void clickContinueBtn(){
        continueButton.click();

    }

}
