import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverLogs;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class AttachmentsHelper {
    @Attachment(value = "{attachName}", type = "img/png")
    public static byte[] attachScreenshot(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{PageSource}", type = "text/plain")
    public static byte[] attachPageSource(){
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{ConsoleLog}", type = "text/plain")
    public static String attachConsoleLog(){
        return String.join(" ", Selenide.getWebDriverLogs(BROWSER));}
}
