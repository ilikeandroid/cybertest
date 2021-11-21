import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CyberWebVacanciesPageTest {

    @BeforeEach
    private void init() {
        open("https://www.cybcube.com/careers/vacancies/");

        if ($("#hs-eu-confirmation-button").exists())
            $("#hs-eu-confirmation-button").click();
    }

    @Test
    public void filterTest() {


        $("#filter-location").selectOptionByValue("Chicago, IL");
        $("#filter-team").selectOptionByValue("Analytics");

        $(".lever-job-title").shouldHave(text("Cyber Threat Analyst"));
    }

    @Test
    public void filterTestWithNoSearchResults() {


        $("#filter-location").selectOptionByValue("Remote");
        $("#filter-team").selectOptionByValue("Industry Engagement");

        $(".rf-lever-empty").shouldHave(text("There are no jobs matching this criteria, please try resetting the filters above."));
    }
}