package testcases.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import testcases.BaseUiTest;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.demoqa.data.PagePaths.*;

@Tags({@Tag("Elements"), @Tag("TextBox")})
@Owner("Serhii Lapin")
@Link(name = "DemoQA - Text Box", url = "https://demoqa.com/text-box")
class TextBoxTest extends BaseUiTest {
    private final String PAGE_URL = BASE_DEMO_QA_UI_URL + TEXT_BOX_PAGE_URL;
    private SelenideElement fullNameField = $(By.id("userName"));
    private SelenideElement emailField = $(By.id("userEmail"));
    private SelenideElement currentAddressField = $(By.cssSelector("textarea#currentAddress"));
    private SelenideElement permanentAddressField = $(By.cssSelector("textarea#permanentAddress"));
    private SelenideElement fullNameOutput = $(By.id("name"));
    private SelenideElement emailOutput = $(By.id("email"));
    private SelenideElement currentAddressOutput = $(By.cssSelector("p#currentAddress"));
    private SelenideElement permanentAddressOutput = $(By.cssSelector("p#permanentAddress"));
    private SelenideElement submitButton = $(By.id("submit"));

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Text Box")
    @DisplayName("Check the ability to submit the Full Name field")
    @Severity(CRITICAL)
    void checkSubmitFullName() {
        String fullName = "Serhii Lapin";
        open(PAGE_URL);
        fullNameField.setValue(fullName);
        submitButton.scrollTo()
                .click();
        fullNameOutput.shouldHave(Condition.text(fullName));
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Text Box")
    @DisplayName("Check the ability to submit the Email field")
    @Severity(CRITICAL)
    void checkSubmitValidEmailField() {
        String email = "test@test.com";
        open(PAGE_URL);
        emailField.setValue(email);
        submitButton.scrollTo()
                .click();
        emailOutput.shouldHave(Condition.text(email));
    }

    @ParameterizedTest(name = "Invalid email: {0}")
    @Tag("P1")
    @Epic("Elements")
    @Feature("Text Box")
    @Story("Check the ability to submit the Email field with invalid data")
    @DisplayName("Check the ability to submit the Email field with invalid data")
    @CsvFileSource(resources = "/test-data/listOfInvalidEmails.csv")
    @Severity(CRITICAL)
    void checkSubmitInvalidEmailField(String invalidEmail) {
        open(PAGE_URL);
        emailField.setValue(invalidEmail);
        submitButton.scrollTo()
                .click();
        emailField.scrollTo()
                .shouldHave(Condition.attribute("class", "mr-sm-2 field-error form-control"));
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Text Box")
    @DisplayName("Check the ability to submit the Current Address field")
    @Severity(CRITICAL)
    void checkSubmitCurrentAddress() {
        String currentAddress = "Budapest, Viola 100, 777";
        open(PAGE_URL);
        currentAddressField.setValue(currentAddress);
        submitButton.scrollTo()
                .click();
        currentAddressOutput.shouldHave(Condition.text(currentAddress));
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Text Box")
    @DisplayName("Check the ability to submit the Permanent Address field")
    @Severity(CRITICAL)
    void checkSubmitPermanentAddress() {
        String permanentAddress = "Budapest, Viola 100, 777";
        open(PAGE_URL);
        permanentAddressField.setValue(permanentAddress);
        submitButton.scrollTo()
                .click();
        permanentAddressOutput.shouldHave(Condition.text(permanentAddress));
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Text Box")
    @DisplayName("Check the ability to submit all text fields")
    @Severity(CRITICAL)
    void checkSubmitAllTextFields() {
        String fullName = "Serhii Lapin";
        String email = "test@test.com";
        String currentAddress = "Budapest, Viola 100, 777";
        String permanentAddress = "Budapest, Viola 150, 1000";
        open(PAGE_URL);
        fullNameField.setValue(fullName);
        emailField.setValue(email);
        currentAddressField.setValue(currentAddress);
        permanentAddressField.setValue(permanentAddress);
        submitButton.scrollTo()
                .click();
        fullNameOutput.shouldHave(Condition.text(fullName));
        emailOutput.shouldHave(Condition.text(email));
        currentAddressOutput.shouldHave(Condition.text(currentAddress));
        permanentAddressOutput.shouldHave(Condition.text(permanentAddress));
    }

    @AfterEach
    public void afterEachTest() {
        takeScreenshotAndAddItToAllureReport();
        closeWebDriver();
    }
}
