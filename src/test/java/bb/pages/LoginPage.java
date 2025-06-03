package bb.pages;
import bb.common.BasePage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//a[@class='getStarted_Sjon']")
    WebElementFacade btnDemo;

    @FindBy(xpath = "...")
    WebElementFacade txtEmail;

    @FindBy(xpath = "...")
    WebElementFacade txtPassword;

    @FindBy(xpath = "...")
    WebElementFacade btnLogin;

    public void clickOnLoginButton(){
        btnDemo.waitUntilClickable().click();
    }

    public LoginPage inoutEmailAddress(String email){
        txtEmail.waitUntilVisible().sendKeys(email);
        return this;
    }

    public LoginPage inoutPassword(String password){
        txtEmail.waitUntilVisible().sendKeys(password);
        return this;
    }

    public LoginPage clickOnLogin(){
        btnLogin.waitUntilClickable().click();
        return this;
    }


}

