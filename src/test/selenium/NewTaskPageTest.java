import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTaskPageTest {
    private WebDriver driver;
    private String baseUrl = "http://localhost:8080/tasks/new";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void testNewTaskPage() {
        // Navigate to the new task page
        driver.get(baseUrl);

        // Fill out the form
        WebElement workersSelect = driver.findElement(By.cssSelector("select[name='workers']"));
        Select workers = new Select(workersSelect);
        workers.selectByVisibleText("Alexander Ivanets");

        WebElement taskTypeSelect = driver.findElement(By.cssSelector("select[name='taskType']"));
        Select taskType = new Select(taskTypeSelect);
        taskType.selectByVisibleText("Новая роль");

        WebElement screenplaySelect = driver.findElement(By.cssSelector("select[name='screenplay']"));
        Select screenplay = new Select(screenplaySelect);
        screenplay.selectByVisibleText("Приветствие");

        WebElement nameInput = driver.findElement(By.cssSelector("input[name='name']"));
        nameInput.sendKeys("New Task");

        WebElement codeInput = driver.findElement(By.cssSelector("input[name='code']"));
        codeInput.sendKeys("001");

        WebElement descriptionTextarea = driver.findElement(By.cssSelector("textarea[name='description']"));
        descriptionTextarea.sendKeys("This is a new task.");

        WebElement kindnessInput = driver.findElement(By.cssSelector("input[name='kindnessImpactValue']"));
        kindnessInput.sendKeys("5");

        WebElement loveInput = driver.findElement(By.cssSelector("input[name='loveImpactValue']"));
        loveInput.sendKeys("2");

        WebElement honestInput = driver.findElement(By.cssSelector("input[name='honestImpactValue']"));
        honestInput.sendKeys("-3");

        // Submit the form
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        // Verify that the new task was created successfully
        WebElement successMessage = driver.findElement(By.cssSelector(".success-message"));
        Assert.assertEquals(successMessage.getText(), "New task created successfully.");
    }
}
