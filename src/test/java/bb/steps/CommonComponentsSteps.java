package bb.steps;

import bb.pages.CommonComponents;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;

public class CommonComponentsSteps extends UIInteractionSteps {

    CommonComponents commonComponents;

    @Step
    public void openBrowser(String name){
        commonComponents.openBrowser(name);
    }
}
