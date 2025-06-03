package bb.defs;

//import bb.steps.CommonSteps;
import bb.steps.LoginSteps;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;

public class LoginDefs {
    @Steps
    LoginSteps loginSteps;

    @Given("user open browser")
    public void userOpenEbankApp(){
        loginSteps.clickOnLoginButton();
        Serenity.takeScreenshot();
    }
}