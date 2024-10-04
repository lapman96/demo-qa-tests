package testcases;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.demoqa.data.PropertiesManager.IS_REMOTE_RUN;
import static org.demoqa.data.PropertiesManager.TEST_BROWSER;

public abstract class BaseUiTest {
    protected static final Logger logger = LogManager.getLogger("ASYNC_CONSOLE_APPENDER");

    @BeforeAll
    public static void beforeAllTests(){
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

    @BeforeEach
    public void beforeEachTest(TestInfo testInfo) {
        logger.info("Starting test: {}", testInfo.getDisplayName());
    }

    @AfterEach
    public void afterEachTest(TestInfo testInfo) {
        takeScreenshotAndAddItToAllureReport();
        logger.info("Finishing test: {}", testInfo.getDisplayName());
        closeWebDriver();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshotAndAddItToAllureReport() {
        return Selenide.screenshot(OutputType.BYTES);
    }

    private static void configureRemoteTestRun() {
        Configuration.remote = "http://77.237.232.129:4444/wd/hub";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.LINUX);
        Configuration.browserCapabilities = desiredCapabilities;
    }
}
