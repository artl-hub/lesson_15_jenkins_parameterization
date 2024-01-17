package com.tests;

import com.Properties.SystemPropertiesTests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class RegistrationRemoteTest {


    @BeforeAll
    static void beforAll() {
        //        Configuration.timeout = 5000;
//        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "chrome (110.0)");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.remote = "https://user1:1234@" + System.getProperty("wdHost", "selenoid.autotests.cloud/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    @Test
    @Tag("demoqa")
    void fillFormTest() {
        String userName = "Alex";
        step("Open form", () -> {
            open("/automation-practice-form");
        });

        step("Fill form", () -> {
            $("#firstName").setValue(userName);
            $("#lastName").setValue("Ivanov");
            $("#userEmail").setValue("art@artem.com");

//        Removing Nuisance Elements
            Selenide.executeJavaScript("$('#fixedban').remove()");
            Selenide.executeJavaScript("$('#adplus-anchor').remove()");
            Selenide.executeJavaScript("$('footer').remove()");

            //     radio-button
            $("#genterWrapper").$(byText("Other")).click();

            //      userNumber
            $("#userNumber").setValue("1234567890");

//        Date
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("July");
            $(".react-datepicker__year-select").selectOption("2008");
            $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

//        Subjects
            $("#subjectsInput").setValue("Math").pressEnter();

//        Hobbies
            $("#hobbiesWrapper").$(byText("Sports")).click();

//        Select picture (upload)
            $("#uploadPicture").uploadFromClasspath("img/1.png");

//        Address
            $("#currentAddress").setValue("Bohnsdor 56");

//        Select State
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();

//        Select City
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Delhi")).click();
            $("#submit").click();

        });

        step("Verify result", () -> {

//        Check
            $(".modal-dialog").should(appear);
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text(userName));
            $(".table-responsive").shouldHave(text("Ivanov"));
            $(".table-responsive").shouldHave(text("art@artem.com"));
            $(".table-responsive").shouldHave(text("Other"));
            $(".table-responsive").shouldHave(text("1234567890"));
            $(".table-responsive").shouldHave(text("30 July,2008"));
            $(".table-responsive").shouldHave(text("Maths"));
            $(".table-responsive").shouldHave(text("Sports"));
            $(".table-responsive").shouldHave(text("1.png"));
            $(".table-responsive").shouldHave(text("Bohnsdor 56"));
            $(".table-responsive").shouldHave(text("NCR Delhi"));


        });
    }
}
