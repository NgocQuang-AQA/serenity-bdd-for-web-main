package bb.defs;

import bb.steps.LoginSteps;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;

public class HomePageDefs {

    @Steps
    LoginSteps loginSteps;

    @Given("user opens HomePage")
    public void userOpenEbankApp(){
        loginSteps.clickOnLoginButton();
        Serenity.takeScreenshot();

    }
}
