import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// hermetyzacja (enkaspulacja) - public / private/ protected
// zagnieżdżanie klas w Java
// przeciążanie nadpisywanie w Java (override, overload)
// Wzorzec projektowy: Page Object Pattern
// Jbehave - pisanie przypadków testowych w notacji Gherkin (Kryteria akceptacji)
public class UltimateQATest extends PageSetup {

    //Testy równolegle - Selenium Grid
    @Test
    public void testOne() {
        WebElement buttonUsingID = driver.findElement(By.id("idExample"));
        buttonUsingID.click();

        WebElement buttonSuccessText = driver.findElement(By.className("entry-title"));
        Assertions.assertEquals("Button success", buttonSuccessText.getText());
    }

    @Test
    // Obsługa wyjątków w Java
    // try catch finally
    public void testTwo() throws InterruptedException {
        WebElement nameTextField = driver.findElement(By.id("et_pb_contact_name_0"));
        nameTextField.sendKeys("Tester");

        WebElement emailTextField = driver.findElement(By.id("et_pb_contact_email_0"));
        emailTextField.sendKeys("tester@tester.com");

        // implicit wait, explicit wait, Thread.sleep
        Thread.sleep(2000);

        WebElement emailMeButton = driver.findElement(By.name("et_builder_submit_button"));
        // sposoby naciśnięcia w element: .click() / Keys.Enter / Actions perform /custom javascript method
        // emailMeButton.sendKeys(Keys.ENTER);
        emailMeButton.click();

        Thread.sleep(2000);

        // sposoby na szukanie elementów na stronie: atrybuty (id, className, name), CSS selector, xpath
        //   //*[] - postać xpath
        //   //div[@class='et-pb-contact-message']/p
        WebElement thanksForContactingUsText = driver.findElement(By.xpath("//div[@class='et-pb-contact-message']/p"));
        Assertions.assertEquals("Thanks for contacting us", thanksForContactingUsText.getText());
    }
}
