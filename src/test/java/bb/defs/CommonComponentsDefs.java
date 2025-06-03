package bb.defs;

import bb.steps.CommonComponentsSteps;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;

public class CommonComponentsDefs {

    @Steps
    CommonComponentsSteps commonComponentsSteps;

    @Given("User open browser with {string}")
    public void userOpenBrowser(String name){
        commonComponentsSteps.openBrowser(name);
    }
}
