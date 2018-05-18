package tests;


import image_driver.ImageDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import steps.MainSteps;

import java.net.MalformedURLException;
import java.net.URL;

public class SlotsTests {
    public MainSteps mainSteps = new MainSteps();
    public AndroidDriver driver;
    public ImageDriver imageDriver;

    @DataProvider(name="Slots")
    public static Object[][] slotsInfo() {
        return new Object[][] {
//                {"1.png", "Bet_10000.png", 0},
//                {"2.png", "Bet_10000.png", 0},
//                {"3.png", "Bet_10800.png", 0},
//                {"4.png", "Bet_24000.png", 0},
//                {"5.png", "Bet_5000.png", 0},
//                {"6.png", "Bet_5000.png", 1},
//                {"7.png", "Bet_5000.png", 1},
//                {"8.png", "Bet_5000.png", 1},
//                {"9.png", "Bet_5000.png", 1},
//                {"10.png", "Bet_5000.png", 1},
                {"11.png", "Bet_5000.png", 2},
//                {"12.png", "Bet_5000.png", 2},
//                {"13.png", "Bet_5000.png", 2},
//                {"14.png", "Bet_5000.png", 2},
//                {"15.png", "Bet_5000.png", 2},
                {"16.png", "Bet_5000.png", 3},
//                {"17.png", "Bet_5000.png", 3},
//                {"18.png", "Bet_5000.png", 3},
//                {"19.png", "Bet_5000.png", 3},
//                {"20.png", "Bet_5000.png", 3},
//                {"21.png", "Bet_5000.png", 3},
                {"22.png", "Bet_5000.png", 4},
//                {"23.png", "Bet_5000.png", 4},
//                {"24.png", "Bet_5000.png", 4},
//                {"25.png", "Bet_5000.png", 4},
//                {"26.png", "Bet_5000.png", 4},
//                {"27.png", "Bet_5000.png", 4},
//                {"28.png", "Bet_5000.png", 5},
                {"29.png", "Bet_5000.png", 5},
//                {"30.png", "Bet_5000.png", 5},
//                {"31.png", "Bet_5000.png", 5},
//                {"32.png", "Bet_5000.png", 5},
//                {"33.png", "Bet_5000.png", 5},
                {"34.png", "Bet_5000.png", 6},
//                {"35.png", "Bet_5000.png", 6},
//                {"36.png", "Bet_500.png", 6},
//                {"37.png", "Bet_5000.png", 6},
//                {"38.png", "Bet_5000.png", 6},
//                {"39.png", "Bet_5000.png", 6},
//                {"40.png", "Bet_5000.png", 6},
                {"41.png", "Bet_5000.png", 7},
//                {"42.png", "Bet_5000.png", 7},
//                {"43.png", "Bet_5000.png", 7},
//                {"44.png", "Bet_5000.png", 7},
//                {"45.png", "Bet_5000.png", 7},
//                {"46.png", "Bet_5000.png", 7},
//                {"47.png", "Bet_5000.png", 7},
                {"48.png", "Bet_5000.png", 8},
//                {"49.png", "Bet_5000.png", 8},
//                {"50.png", "Bet_5000.png", 8},
//                {"51.png", "Bet_5000.png", 8},
//                {"52.png", "Bet_5000.png", 8},
//                {"53.png", "Bet_5000.png", 8},
//                {"54.png", "Bet_5000.png", 9},
                {"55.png", "Bet_5000.png", 9},
//                {"56.png", "Bet_5000.png", 9},
//                {"57.png", "Bet_5000.png", 9},
//                {"58.png", "Bet_5000.png", 9},
//                {"59.png", "Bet_5000.png", 9},
//                {"60.png", "Bet_5000.png", 9},
//                {"61.png", "Bet_5000.png", 9},
                {"62.png", "Bet_5000.png", 10},
//                {"63.png", "Bet_5000.png", 10},
//                {"64.png", "Bet_5000.png", 10},
//                {"65.png", "Bet_5000.png", 10},
//                {"66.png", "Bet_5000.png", 10},
//                {"67.png", "Bet_5000.png", 10},
                {"68.png", "Bet_5000.png", 11},
//                {"69.png", "Bet_5000.png", 11}

        };
    }

    @BeforeSuite
    public void beforeSuite() throws Throwable {
        mainSteps.beforeScenarioStart();
        mainSteps.selectPlayAsGuest();
        mainSteps.clickAllSlots();
    }

    @AfterSuite
    public void afterSuite(){
        mainSteps.afterScenarioFinish();
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        mainSteps.back();
    }

    @Test(dataProvider="Slots")
    public void slotsTest(String slotName, String bet, int timesToSwipe) throws Throwable {
        mainSteps.swipeScreen(timesToSwipe);
        mainSteps.chooseSlot(slotName);
        mainSteps.chooseBet(bet);
        mainSteps.initAllElements();
//        mainSteps.find_element_and_click();
//        mainSteps.find_disappeared_elements();
//        mainSteps.find_appeared_elements();
    }
}
