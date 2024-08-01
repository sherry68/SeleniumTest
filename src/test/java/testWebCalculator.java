import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class testWebCalculator {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        String chromeDriverVersion = "126.0.6478.127";
        WebDriverManager.chromedriver().driverVersion(chromeDriverVersion).setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() {
        try {
            // Open the Carbohydrate Calculator webpage
            driver.get("https://www.calculator.net/carbohydrate-calculator.html");

            // Find the input fields and buttons
            WebElement MetricUnit = driver.findElement(By.id("menuon"));
            MetricUnit.click();

            WebElement ageInput = driver.findElement(By.id("cage"));
            WebElement maleRadioButton = driver.findElement(By.xpath("//input[@name='csex' and @value='m']"));
            WebElement femaleRadioButton = driver.findElement(By.xpath("//input[@name='csex' and @value='f']"));
            WebElement cheightMeterInput = driver.findElement(By.id("cheightmeter"));
            WebElement weightInput = driver.findElement(By.id("ckg"));
            WebElement activityDropdown = driver.findElement(By.id("cactivity"));
            WebElement calculateButton = driver.findElement(By.xpath("//input[@value='Calculate']"));

            // Fill out the form
            ageInput.clear();
            ageInput.sendKeys("17");
            //maleRadioButton.click();
            cheightMeterInput.clear();
            cheightMeterInput.sendKeys("180");
            weightInput.clear();
            weightInput.sendKeys("70");
            activityDropdown.findElement(By.xpath("//*[@id=\"cactivity\"]/option[2]")).click();

            // Submit the form
            calculateButton.click();

            // Wait for results and verify
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement resultElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div"));

            // Assert the result
            Assertions.assertEquals("Please provide an age between 18 and 80.", resultElement.getText());
            System.out.println("Result: " + resultElement.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

