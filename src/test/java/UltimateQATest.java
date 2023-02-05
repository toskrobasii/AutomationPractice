import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testThree() {
        List<WebElement> listOfQATitles = driver.findElements(By.xpath("//table[@id='htmlTableId']//tr"));
        Assertions.assertEquals(4, listOfQATitles.size());

        // //table[@id='htmlTableId']//tr[1]/td[1]
        // //table[@id='htmlTableId']//tr[4]/td[3]
    }

    // nadawanie grup testom np. biblioteka TestNG
//    @org.testng.annotations.Test(groups = {"smoke test"})
    @Test()
    public void testFour() {
        List<String> listOfCars = new ArrayList<>();
        listOfCars.add("volvo");
        listOfCars.add("saab");
        listOfCars.add("opel");
        listOfCars.add("audi");

        WebElement dropdownOfCars = driver.findElement(By.xpath("//div[text()='Select an option and validate that it is selected']/select"));

        // wyrażenia lambda
        for(int i = 0 ; i < listOfCars.size(); i++) {
            dropdownOfCars.click();

//            System.out.println("//option[@value='" + listOfCars.get(i) + "']");
            WebElement dropdownOfCarsOption = driver.findElement(By.xpath("//option[@value='" + listOfCars.get(i) + "']"));
            dropdownOfCarsOption.click();
            Assertions.assertTrue(dropdownOfCarsOption.isSelected());
            Assertions.assertEquals(listOfCars.get(i), dropdownOfCarsOption.getText().toLowerCase());
        }
    }

    //  //input[@type='checkbox' and @value='Bike']
    //  //div[contains(text(), 'Select an option')]
    //  //div[contains(text(), 'and validate that')]
    // ancestor, following-sibling, preceeding-sibling

}
