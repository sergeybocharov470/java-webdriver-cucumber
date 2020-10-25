package support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

import static support.TestContext.getDriver;
import static support.TestContext.getConfig;
import static support.TestContext.getUsers;

public class Hooks {

    @Before(order = 0)
    public void scenarioStart() {
        TestContext.initialize();
//////////////addedDay19/////////////////
        TestContext.setTimestamp();
		
//        getDriver().manage().timeouts().pageLoadTimeout(getConfig().pageLoadTimeout, TimeUnit.SECONDS);		
/////////////////////////////////////////						   
        getDriver().manage().timeouts().implicitlyWait(getConfig().implicitTimeout, TimeUnit.SECONDS);
        getDriver().manage().deleteAllCookies();
    }

    @After(order = 0)
    public void scenarioEnd(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();
            byte[] screenshot = screenshotTaker.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        TestContext.teardown();
    }
}
