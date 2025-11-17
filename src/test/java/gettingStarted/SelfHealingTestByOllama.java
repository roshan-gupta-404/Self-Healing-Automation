package gettingStarted;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ai.LoginPage;
import utilities.OllamaHelper;
import utilities.OpenAIHelper;


public class SelfHealingTestByOllama {

    private OpenAIClient client;
    private WebDriver driver;

    @BeforeTest
    public void Setup() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("OPENAI_API_KEY");

        client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();

        driver = new ChromeDriver();

        driver.navigate().to("http://eaapp.somee.com/Account/Login");
    }

    @Test
    public void testLoginForUserFromAI() throws Exception {
        System.out.println("Testing User Login from AI Locators");

        var jsonResponse = OllamaHelper.GetLocatorsForPageAsJson(driver.getPageSource());

        LoginPage loginPage = new LoginPage(driver, jsonResponse);
        loginPage.performLogin("admin", "password");
    }

}
