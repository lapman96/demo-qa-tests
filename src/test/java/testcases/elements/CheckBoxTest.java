package testcases.elements;

import io.qameta.allure.*;
import org.demoqa.pageobjects.elements.CheckBoxPage;
import org.junit.jupiter.api.*;
import testcases.BaseUiTest;

import java.util.List;

import static io.qameta.allure.SeverityLevel.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tags({@Tag("Elements"), @Tag("Checkbox")})
@Owner("Serhii Lapin")
@Link(name = "DemoQA - Check Box", url = "https://demoqa.com/checkbox")
class CheckBoxTest extends BaseUiTest {

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Check Box")
    @DisplayName("Check the ability to expand all checkboxes by clicking on the Expand All button")
    @Severity(NORMAL)
    void checkTheAbilityToExpandAllCheckboxes() {
        CheckBoxPage checkBoxElementPage = new CheckBoxPage();

        Allure.step("Open the CheckBox page", checkBoxElementPage::openPage);
        Allure.step("Click on the Expand All Checkboxes button", checkBoxElementPage::clickOnTheExpandAllCheckboxesButton);
        Allure.step("Verify the number of visible checkboxes", () -> {
            int numberOfVisibleCheckboxes = checkBoxElementPage.getVisibleCheckboxes().size();
            assertThat(numberOfVisibleCheckboxes).isEqualTo(17);
        });
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Check Box")
    @DisplayName("Check the ability to collapse all checkboxes by clicking on the Collapse All button")
    @Severity(NORMAL)
    void checkTheAbilityToCollapseAllCheckboxes() {
        CheckBoxPage checkBoxElementPage = new CheckBoxPage();

        Allure.step("Open the CheckBox page", checkBoxElementPage::openPage);
        Allure.step("Click on the Expand All Checkboxes button", checkBoxElementPage::clickOnTheExpandAllCheckboxesButton);
        Allure.step("Click on the Collapse All Checkboxes button", checkBoxElementPage::clickOnTheCollapseAllCheckboxesButton);
        Allure.step("Verify the number of visible checkboxes", () -> {
            int numberOfVisibleCheckboxes = checkBoxElementPage.getVisibleCheckboxes().size();
            assertThat(numberOfVisibleCheckboxes).isEqualTo(1);
        });
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Check Box")
    @DisplayName("Check the ability to tick all checkboxes by clicking on the root checkbox")
    @Severity(CRITICAL)
    void checkTheAbilityToTickAllCheckboxesByClickingRootCheckbox() {
        List<String> expectedResult = List.of("home", "desktop", "notes", "commands", "documents", "workspace",
                "react", "angular", "veu", "office", "public", "private", "classified", "general", "downloads",
                "wordFile", "excelFile");

        CheckBoxPage checkBoxElementPage = new CheckBoxPage();

        Allure.step("Open the CheckBox page", checkBoxElementPage::openPage);
        Allure.step("Tick the root checkbox", () -> checkBoxElementPage.tickTheCheckboxByName("Home"));
        Allure.step("Verify the selected checkboxes", () -> {
            assertThat(checkBoxElementPage.getSelectedCheckboxes().texts()).containsAll(expectedResult);
        });
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Check Box")
    @DisplayName("Check the ability to tick all checkboxes inside the folder by clicking on the folder checkbox")
    @Severity(CRITICAL)
    void checkTheAbilityToTickAllCheckboxesInTheDefinedFolder() {
        List<String> expectedResult = List.of("desktop", "notes", "commands");

        CheckBoxPage checkBoxElementPage = new CheckBoxPage();

        Allure.step("Open the CheckBox page", checkBoxElementPage::openPage);
        Allure.step("Click on the Expand All Checkboxes button", checkBoxElementPage::clickOnTheExpandAllCheckboxesButton);
        Allure.step("Tick the folder checkbox", () -> checkBoxElementPage.tickTheCheckboxByName("Desktop"));
        Allure.step("Verify the selected checkboxes", () -> {
            assertThat(checkBoxElementPage.getSelectedCheckboxes().texts()).containsAll(expectedResult);
            assertThat(checkBoxElementPage.isCheckboxHalfChecked("Home")).isTrue();
        });
    }

    @Test
    @Tag("P1")
    @Epic("Elements")
    @Feature("Check Box")
    @DisplayName("Check the ability to untick some checkboxes")
    @Severity(CRITICAL)
    void checkTheAbilityToUntickSomeCheckboxes() {
        List<String> expectedResult = List.of("commands", "documents", "office", "workspace",
                "react", "angular", "veu", "private", "classified", "general", "downloads",
                "wordFile", "excelFile");

        CheckBoxPage checkBoxElementPage = new CheckBoxPage();

        Allure.step("Open the CheckBox page", checkBoxElementPage::openPage);
        Allure.step("Click on the Expand All Checkboxes button", checkBoxElementPage::clickOnTheExpandAllCheckboxesButton);
        Allure.step("Tick the root checkbox", () -> checkBoxElementPage.tickTheCheckboxByName("Home"));
        Allure.step("Untick the 'Notes' checkbox", () -> checkBoxElementPage.untickTheCheckboxByName("Notes"));
        Allure.step("Verify the selected checkboxes", () -> {
            assertThat(checkBoxElementPage.getSelectedCheckboxes().texts()).containsAll(expectedResult);
            assertThat(checkBoxElementPage.isCheckboxUnchecked("Notes")).isTrue();
            assertThat(checkBoxElementPage.isCheckboxHalfChecked("Home")).isTrue();
            assertThat(checkBoxElementPage.isCheckboxHalfChecked("Desktop")).isTrue();
        });
    }
}