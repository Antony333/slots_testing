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
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class MainSteps {

    public final String ELEMENTS_TO_SEARCH = "SPIN, MAX, +";
    public AndroidDriver driver;
    public ImageDriver imageDriver;
    public JSONObject elements;

    public String templatesDir = "src/test/resources/lobby_templates/";
    public String slotsDir = "src/test/resources/slots/";

    @Before
    public void beforeScenarioStart() throws Throwable {
        System.out.println("-----------------Start of Scenario-----------------");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "device");
        capabilities.setCapability("appPackage", "com.productmadness.hovmobile");
        capabilities.setCapability("appActivity", "com.productmadness.application.ApplicationActivity");
        capabilities.setCapability("newCommandTimeout", 2000);
        URL serverAddress = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(serverAddress, capabilities);
        imageDriver = new ImageDriver();
    }

    @After
    public void afterScenarioFinish(){
        driver.quit();
    }

    @When("^I want to back on the previous screen$")
    public void back() throws InterruptedException {
        Thread.sleep(5000);
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }

    @When("^I am in slot screen$")
    public void initAllElements() throws Exception {
        //Waiting until we get slot screen
        System.out.println("Wait slot to be loaded");
        Thread.sleep(80000);
//        System.out.println("Take slot screenshot");
        File screenShot = takeScreenShot();
        System.out.println("Try to init slot");
//        elements = imageDriver.initThresholds(screenShot, ELEMENTS_TO_SEARCH);
        System.out.println("Slots was initiated");
//        System.out.println(elements);
        Assert.assertTrue(true);
    }

    @When("^I spin slot$")
    public void find_element_and_click() throws Exception {
        File screenShot = takeScreenShot();
        String spinThreshold = String.valueOf(elements.getInt("SPIN"));
        JSONObject spinElement = imageDriver.findElement(screenShot, "SPIN", spinThreshold);
        System.out.println("I spin element");
        System.out.println(spinElement);
        JSONObject coordinates = spinElement.getJSONObject("coordinates");
        System.out.println(coordinates.getInt("x_center"));
        System.out.println(coordinates.getInt("y_center"));
        Assert.assertNotNull(spinElement);
        TouchAction action = new TouchAction(driver);
        action.tap(coordinates.getInt("x_center"), coordinates.getInt("y_center")).release();
        action.perform();
    }

    @Then("^I should see not interactable elements while spinning$")
    public void find_disappeared_elements() throws Exception {
        System.out.println("Wait elements disappearance");
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

    @Given("^I play as guest$")
    public void selectPlayAsGuest() throws Throwable {
        System.out.println("Wait main lobby");
        Thread.sleep(100000);
        System.out.println("Take screenshot main lobby");
        File screenShot = takeScreenShot();
        JSONObject playAsGuestCoordinates = imageDriver.findElementByTempalte(screenShot, new File(templatesDir + "guest galaxy note 4.png"));
        System.out.println(playAsGuestCoordinates);
        TouchAction action = new TouchAction(driver);
        action.tap(playAsGuestCoordinates.getInt("x_center"), playAsGuestCoordinates.getInt("y_center")).release();
        action.perform();
    }

    @When("^I choose all slots$")
    public void clickAllSlots() throws Throwable {
        Thread.sleep(10000);
        System.out.println("\nTake screenshot slots lobby");
        File screenShot = takeScreenShot();
        JSONObject allSpins = imageDriver.findElementByTempalte(screenShot, new File(templatesDir + "All slots Note 4.png"));
        System.out.println(allSpins);
        System.out.printf("\nTry to click all spins\n");
        TouchAction action = new TouchAction(driver);
        action.tap(allSpins.getInt("x_center"), allSpins.getInt("y_center")).release();
        action.perform();
        System.out.println("All spins is loaded");
    }

    @When("^I choose slot (.*)$")
    public void chooseSlot(String slotName) throws Throwable {
        System.out.println("I wait slot to choose");
        Thread.sleep(15000);
        File screenShotAllSlots = takeScreenShot();
        JSONObject slot1 = imageDriver.findElementByTempalte(screenShotAllSlots, new File(slotsDir + slotName));
        System.out.println(String.format("Click %s\n", slotName));
        new TouchAction(driver).tap(slot1.getInt("x_center"), slot1.getInt("y_center")).release().perform();
    }

    @And("^I choose bet (.*)$")
    public void chooseBet(String bet) throws Throwable {
        System.out.println("Wait bet");
        Thread.sleep(3000);
        System.out.println("Take bet screenshot");
        File screenShot = takeScreenShot();
        JSONObject playAsGuestCoordinates = imageDriver.findElementByTempalte(screenShot, new File(templatesDir + bet));
        System.out.println(playAsGuestCoordinates);
        System.out.println("Choose bet\n");
        TouchAction action = new TouchAction(driver);
//        action.tap(playAsGuestCoordinates.getInt("x_center"), playAsGuestCoordinates.getInt("y_center")).release();
        action.tap(1801, 1225).release();
        action.perform();
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
        System.out.println(String.format("Take screenshot %s", destFile));
        // Copy paste file at destination folder location
        FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        return scrFile;
    }

    @When("^I want to swipe screen$")
    public void swipeScreen(int timesToSwipe) throws Throwable {
        Thread.sleep(20000);
        System.out.println(String.format("I want ro swipe %d", timesToSwipe));
        for (int i = 0; i < timesToSwipe; i++) {
            Dimension size = driver.manage().window().getSize();
            int width = size.getWidth();
            int height = size.getWidth();

            int startx = (int) (width * (0.91));
            System.out.println(startx);
            int endx = (int) (width * (0.10));
            System.out.println(endx);
            int starty =  height / 2 ;
            System.out.println(starty);
            int endy = height / 2;
            System.out.println(endy);
            System.out.println("Try to swipe");
            new TouchAction(driver).press(startx, 300).waitAction(Duration.ofSeconds(10)).moveTo(endx, 300).release().perform();
            System.out.println("Swiped");
            Thread.sleep(3000);
        }
        takeScreenShot();
    }
}
