package testcases;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.demoqa.data.PropertiesManager.IS_REMOTE_RUN;
import static org.demoqa.data.PropertiesManager.TEST_BROWSER;

public abstract class BaseUiTest {
    @BeforeAll
    public static void setUp(){
        if ("true".equals(IS_REMOTE_RUN)) {
            configureRemoteTestRun();
        }

        Configuration.browser = TEST_BROWSER;
        Configuration.timeout = 10000;
        Configuration.screenshots = true;
        Configuration.fastSetValue = false;
        Configuration.headless = true;
        Configuration.reopenBrowserOnFail = true;
    }

    private static void configureRemoteTestRun() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.WIN11);
        Configuration.browserCapabilities = desiredCapabilities;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshotAndAddItToAllureReport() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}
