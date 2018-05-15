package madness;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BaseTest {
    protected AppiumDriver driver;

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
        this.driver = new AndroidDriver(serverAddress, capabilities);
    }

    @After
    public void tearDown() {
        this.driver.quit();
    }
}
