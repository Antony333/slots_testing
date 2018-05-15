package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import image_driver.ImageDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
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

    public final String ELEMENTS_TO_SEARCH = "SPIN, MAX, +";
    public AndroidDriver driver;
    public ImageDriver imageDriver;
    public JSONObject elements;


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
        imageDriver = new ImageDriver();
    }

    @Given("^I am in slot screen$")
    public void init_all_elements() throws Exception {
        //Waiting until we get slot screen
        Thread.sleep(150000);
        File screenShot = takeScreenShot();
        elements = imageDriver.initThresholds(screenShot, ELEMENTS_TO_SEARCH);
        System.out.println(elements);
        Assert.assertTrue(true);

    }

    @When("^I spin slot$")
    public void find_element_and_click() throws Exception {
        File screenShot = takeScreenShot();
        String spinThreshold = String.valueOf(elements.getInt("SPIN"));
        JSONObject spinElement = imageDriver.findElement(screenShot, "SPIN", spinThreshold);

        System.out.println(spinElement);
        JSONObject coordinates = spinElement.getJSONObject("coordinates");

        Assert.assertNotNull(spinElement);
        TouchAction action = new TouchAction(driver);
        action.tap(coordinates.getInt("x_center"), coordinates.getInt("y_center")).release();
        action.perform();
    }

    @Then("^I should see not interactable elements while spinning$")
    public void find_disappeared_elements() throws Exception {
        Thread.sleep(2000);
        File screenShot = takeScreenShot();
        String spinThreshold = String.valueOf(elements.getInt("SPIN"));
        JSONObject spinElement = imageDriver.findElement(screenShot, "SPIN", spinThreshold);
        System.out.println(spinElement);
        Assert.assertNull(spinElement);

        String maxBetThreshold = String.valueOf(elements.getInt("MAX"));
        JSONObject maxBetElement = imageDriver.findElement(screenShot, "MAX", maxBetThreshold);
        System.out.println(maxBetElement);
        Assert.assertNull(maxBetElement);

        String plusThreshold = String.valueOf(elements.getInt("+"));
        JSONObject plusElement = imageDriver.findElement(screenShot, "+", plusThreshold);
        System.out.println(plusElement);
        Assert.assertNull(plusElement);
    }

    @And("^I should see interactable elements when spin has come to a stop$")
    public void find_appeared_elements() throws Exception {
        Thread.sleep(2000);
        File screenShot = takeScreenShot();
        String spinThreshold = String.valueOf(elements.getInt("SPIN"));
        JSONObject spinElement = imageDriver.findElement(screenShot, "SPIN", spinThreshold);
        System.out.println(spinElement);
        Assert.assertNotNull(spinElement);

        String maxBetThreshold = String.valueOf(elements.getInt("MAX"));
        JSONObject maxBetElement = imageDriver.findElement(screenShot, "MAX", maxBetThreshold);
        System.out.println(maxBetElement);
        Assert.assertNotNull(maxBetElement);

        String plusThreshold = String.valueOf(elements.getInt("+"));
        JSONObject plusElement = imageDriver.findElement(screenShot, "+", plusThreshold);
        Assert.assertNotNull(plusElement);
        System.out.println(plusElement);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public File takeScreenShot() throws IOException {

        // Set folder name to store screenshots.
        String destDir = "src/test/resources/screenshots";
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
            // Copy paste file at destination folder location
        FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        return scrFile;
    }
}
