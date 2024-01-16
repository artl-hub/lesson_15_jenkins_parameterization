package com.Properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
       String browser = System.getProperty("browser", "mozilla");

        System.out.println(browser); //
        //gradle property_test
        //mozila

        // gradle property_test -Dbrowser=opera
        // opera
    }


}
