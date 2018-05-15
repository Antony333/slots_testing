package madness.steps;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainSteps {

    public File screenShot;
    public static AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "device");
        capabilities.setCapability("appPackage", "com.productmadness.hovmobile");
        capabilities.setCapability("appActivity", "com.productmadness.application.ApplicationActivity");
        capabilities.setCapability("newCommandTimeout", 200);
        URL serverAddress = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(serverAddress, capabilities);
    }

    @Given("^I am in slot screen$")
    public void init_all_elements() throws Exception {
        Thread.sleep(140000);
        takeScreenShot();

    }

    @When("^I spin slot$")
    public void find_element() throws Exception {
    }

    @Then("^I should see UI not interactable$")
    public void find_elements() throws Exception {
        Assert.assertTrue(true);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void takeScreenShot() {

        // Set folder name to store screenshots.
        String destDir = "screenshots";
        String screenNamePattern = "dd-MMM-yyyy__hh_mm_ssaa";
        String screenFileExtension = ".png";

        // Capture screenshot.
        File scrFile = driver.getScreenshotAs(OutputType.FILE);

        // Set date format to set It as screenshot file name.
        SimpleDateFormat dateFormat = new SimpleDateFormat(screenNamePattern);

        // Create folder under project with name "screenshots" provided to destDir.
        new File(destDir).mkdirs();

        // Set file name using current date time.
        String destFile = dateFormat.format(new Date()) + screenFileExtension;
        try {
            // Copy paste file at destination folder location
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
