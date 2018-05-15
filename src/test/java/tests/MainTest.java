package madness.tests;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainTest extends BaseTest {

    @Test
    public void testFindViewSuccess() throws InterruptedException {
        Thread.sleep(140000);
        takeScreenShot();
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