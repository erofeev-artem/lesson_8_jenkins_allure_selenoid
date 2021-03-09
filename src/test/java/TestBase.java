import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class TestBase {
    @BeforeAll
    public static void setup() {
        Configuration.browser = System.getProperty("browser", "chrome");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        addListener("AllureSelenide", new AllureSelenide());
        Configuration.remote = System.getProperty("remote_driver");
    }

    @AfterEach
    public void afterEach() {
        AttachmentsHelper.attachScreenshot("Last screenshot");
        AttachmentsHelper.attachAsText("Browser console logs", AttachmentsHelper.getConsoleLog());
        AttachmentsHelper.attachPageSource();
        AttachmentsHelper.attachVideo();
    }
}
