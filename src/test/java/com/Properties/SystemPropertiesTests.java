package com.Properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class SystemPropertiesTests {

    @Test
    void systemPropertiesTest() {
        String browser = System.getProperty("browser");

        System.out.println(browser); // null
    }

    @Test
    void systemProperties1Test() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser");

        System.out.println(browser); // chrome
    }

    @Test
    void systemProperties2Test() {

        String browser = System.getProperty("browser", "mozilla");

        System.out.println(browser); // mozilla
    }

    @Test
    void systemProperties3Test() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser", "mozilla");

        System.out.println(browser); // chrome
    }

    @Test
    @Tag("property")
    void systemProperties4Test() {
       String browser = System.getProperty("browser", "mozilla"); // устанавливается дефолтное значени

        System.out.println(browser); //
        //gradle property_test
        //mozila

        // gradle property_test -Dbrowser=opera
        // opera
    }

    @Test
    @Tag("hello")
    void systemProperties5Test() {
        String name = System.getProperty("name", "default student");
        String message = format("Hello, %s!", name);

        System.out.println(message); //
        //gradle hello_test
        //Hello, default student!

        //gradle hello_test "-Dname=Alex Egorov"
        //gradle hello_test -Dname="Alex Egorov"
        //if gradle hello_test -Dname=Alex Egorov  - will be BUILD FAILED: Task 'Egorov' not found in foot project


    }

    @Test
    @Tag("main")
    void systemPropertiesBaseUrl() {
        String baseUrl = System
                .getProperty("baseUrl", "https://demoqa.com");
    }

    @Test
    @Tag("main")
    void systemPropertiesBrowserSize() {
        String browserSize = System
                .getProperty("browserSize", "1920x1080");
    }

    @Test
    @Tag("main")
    void systemPropertiesRemoteBrowser() {
        String remoteBrowser = System
                .getProperty("remoteBrowser", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
    }



}
