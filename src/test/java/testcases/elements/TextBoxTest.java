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
        Allure.step("Open the TextBox page", () -> open(PAGE_URL));
        Allure.step("Set Full Name field", () -> fullNameField.setValue(fullName));
        Allure.step("Click Submit button", () -> submitButton.scrollTo().click());
        Allure.step("Verify Full Name output", () -> fullNameOutput.shouldHave(Condition.text(fullName)));
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Text Box")
    @DisplayName("Check the ability to submit the Email field")
    @Severity(CRITICAL)
    void checkSubmitValidEmailField() {
        String email = "test@test.com";
        Allure.step("Open the TextBox page", () -> open(PAGE_URL));
        Allure.step("Set Email field", () -> emailField.setValue(email));
        Allure.step("Click Submit button", () -> submitButton.scrollTo().click());
        Allure.step("Verify Email output", () -> emailOutput.shouldHave(Condition.text(email)));
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
        Allure.step("Open the TextBox page", () -> open(PAGE_URL));
        Allure.step("Set invalid Email field", () -> emailField.setValue(invalidEmail));
        Allure.step("Click Submit button", () -> submitButton.scrollTo().click());
        Allure.step("Verify Email field has error class", () ->
                emailField.scrollTo().shouldHave(Condition.attribute("class", "mr-sm-2 field-error form-control"))
        );
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Text Box")
    @DisplayName("Check the ability to submit the Current Address field")
    @Severity(CRITICAL)
    void checkSubmitCurrentAddress() {
        String currentAddress = "Budapest, Viola 100, 777";
        Allure.step("Open the TextBox page", () -> open(PAGE_URL));
        Allure.step("Set Current Address field", () -> currentAddressField.setValue(currentAddress));
        Allure.step("Click Submit button", () -> submitButton.scrollTo().click());
        Allure.step("Verify Current Address output", () ->
                currentAddressOutput.shouldHave(Condition.text(currentAddress))
        );
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Text Box")
    @DisplayName("Check the ability to submit the Permanent Address field")
    @Severity(CRITICAL)
    void checkSubmitPermanentAddress() {
        String permanentAddress = "Budapest, Viola 100, 777";
        Allure.step("Open the TextBox page", () -> open(PAGE_URL));
        Allure.step("Set Permanent Address field", () -> permanentAddressField.setValue(permanentAddress));
        Allure.step("Click Submit button", () -> submitButton.scrollTo().click());
        Allure.step("Verify Permanent Address output", () ->
                permanentAddressOutput.shouldHave(Condition.text(permanentAddress))
        );
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
        Allure.step("Open the TextBox page", () -> open(PAGE_URL));
        Allure.step("Set Full Name field", () -> fullNameField.setValue(fullName));
        Allure.step("Set Email field", () -> emailField.setValue(email));
        Allure.step("Set Current Address field", () -> currentAddressField.setValue(currentAddress));
        Allure.step("Set Permanent Address field", () -> permanentAddressField.setValue(permanentAddress));
        Allure.step("Click Submit button", () -> submitButton.scrollTo().click());
        Allure.step("Verify Full Name output", () -> fullNameOutput.shouldHave(Condition.text(fullName)));
        Allure.step("Verify Email output", () -> emailOutput.shouldHave(Condition.text(email)));
        Allure.step("Verify Current Address output", () ->
                currentAddressOutput.shouldHave(Condition.text(currentAddress))
        );
        Allure.step("Verify Permanent Address output", () ->
                permanentAddressOutput.shouldHave(Condition.text(permanentAddress))
        );
    }
}