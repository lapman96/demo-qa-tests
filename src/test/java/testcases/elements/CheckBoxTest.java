package testcases.elements;

import org.demoqa.pageobjects.elements.CheckBoxPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testcases.BaseUiTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Elements")
class CheckBoxTest extends BaseUiTest {

    @Tag("P1")
    @Test
    void checkTheAbilityToExpandAllCheckboxes() {
        CheckBoxPage checkBoxElementPage = new CheckBoxPage();
        checkBoxElementPage
                .openPage()
                .clickOnTheExpandAllCheckboxesButton();

        int numberOfVisibleCheckboxes = checkBoxElementPage
                .getVisibleCheckboxes()
                .size();

        assertThat(numberOfVisibleCheckboxes).isEqualTo(17);
    }

    @Tag("P1")
    @Test
    void checkTheAbilityToCollapseAllCheckboxes() {
        CheckBoxPage checkBoxElementPage = new CheckBoxPage();

        checkBoxElementPage
                .openPage()
                .clickOnTheExpandAllCheckboxesButton()
                .clickOnTheCollapseAllCheckboxesButton();

        int numberOfVisibleCheckboxes = checkBoxElementPage
                .getVisibleCheckboxes()
                .size();

        assertThat(numberOfVisibleCheckboxes).isEqualTo(1);
    }

    @Tag("P1")
    @Test
    void checkTheAbilityToTickAllCheckboxesByClickingRootCheckbox() {
        List<String> expectedResult = List.of("home", "desktop", "notes", "commands", "documents", "workspace",
                "react", "angular", "veu", "office", "public", "private", "classified", "general", "downloads",
                "wordFile", "excelFile");

        CheckBoxPage checkBoxElementPage = new CheckBoxPage();
        checkBoxElementPage
                .openPage()
                .tickTheCheckboxByName("Home");

        assertThat(checkBoxElementPage.getSelectedCheckboxes().texts()).containsAll(expectedResult);
    }

    @Tag("P1")
    @Test
    void checkTheAbilityToTickAllCheckboxesInTheDefinedFolder() {
        List<String> expectedResult = List.of( "desktop", "notes", "commands");

        CheckBoxPage checkBoxElementPage = new CheckBoxPage();

        checkBoxElementPage
                .openPage()
                .clickOnTheExpandAllCheckboxesButton()
                .tickTheCheckboxByName("Desktop");

        assertThat(checkBoxElementPage.getSelectedCheckboxes().texts()).containsAll(expectedResult);
        assertThat(checkBoxElementPage.isCheckboxHalfChecked("Home")).isTrue();
    }

    @Tag("P1")
    @Test
    void checkTheAbilityToUntickSomeCheckboxes() {
        List<String> expectedResult = List.of("commands", "documents","office", "workspace",
                "react", "angular", "veu", "private", "classified", "general", "downloads",
                "wordFile", "excelFile");

        CheckBoxPage checkBoxElementPage = new CheckBoxPage();
        checkBoxElementPage
                .openPage()
                .clickOnTheExpandAllCheckboxesButton()
                .tickTheCheckboxByName("Home")
                .untickTheCheckboxByName("Notes");

        assertThat(checkBoxElementPage.getSelectedCheckboxes().texts()).containsAll(expectedResult);
        assertThat(checkBoxElementPage.isCheckboxUnchecked("Notes")).isTrue();
        assertThat(checkBoxElementPage.isCheckboxHalfChecked("Home")).isTrue();
        assertThat(checkBoxElementPage.isCheckboxHalfChecked("Desktop")).isTrue();
    }
}
