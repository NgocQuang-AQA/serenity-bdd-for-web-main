package bb;
import org.junit.platform.suite.api.*;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "bb.defs") // package step defs
//@ConfigurationParameter(key = "cucumber.filter.tags", value = "@TC_2")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, io.cucumber.core.plugin.SerenityReporter")
//@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, io.cucumber.core.plugin.SerenityReporterParallel")
public class TestRunner {

}
