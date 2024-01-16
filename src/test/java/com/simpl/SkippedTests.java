package com.simpl;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@Tag("simple")
public class SkippedTests {

    @Test
    @Disabled
    void someTest() {
        assertTrue(false);
    }

    @Test
    @Disabled("Some reason")
    void someTest1() {
        assertTrue(false);
    }

}
