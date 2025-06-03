package bb.common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CommonHooks {
    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
    }

    @After(value = "@NeedCleanup")
    public void afterScenarioWithCleanup(Scenario scenario) {
        System.out.println("Cleanup for scenario: " + scenario.getName());
    }
}
