package com.dolphindalt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple unit tests to verify the test framework is working.
 *
 * Note: Testing the plugin class directly requires the Hytale server
 * environment to be initialized, so these are basic standalone tests
 * that demonstrate JUnit 5 functionality.
 */
public class MyPluginTest {

    @Test
    @DisplayName("Basic arithmetic test")
    public void testBasicArithmetic() {
        // Simple dummy test to verify test framework is working
        assertEquals(4, 2 + 2, "2 + 2 should equal 4");
        assertTrue(5 > 3, "5 should be greater than 3");
        assertFalse(10 < 5, "10 should not be less than 5");
    }

    @Test
    @DisplayName("String operations test")
    public void testStringOperations() {
        String pluginName = "MyPlugin";
        assertEquals(8, pluginName.length(), "Plugin name length should be 8");
        assertTrue(pluginName.startsWith("My"), "Plugin name should start with 'My'");
        assertTrue(pluginName.endsWith("Plugin"), "Plugin name should end with 'Plugin'");
    }

    @Test
    @DisplayName("List operations test")
    public void testListOperations() {
        List<String> items = new ArrayList<>();
        assertTrue(items.isEmpty(), "New list should be empty");

        items.add("First");
        items.add("Second");

        assertEquals(2, items.size(), "List should have 2 items");
        assertTrue(items.contains("First"), "List should contain 'First'");
        assertFalse(items.contains("Third"), "List should not contain 'Third'");
    }

    @ParameterizedTest
    @ValueSource(strings = {"hello", "world", "test"})
    @DisplayName("Parameterized test example")
    public void testStringsAreNotEmpty(String value) {
        assertNotNull(value, "Value should not be null");
        assertFalse(value.isEmpty(), "Value should not be empty");
    }
}
