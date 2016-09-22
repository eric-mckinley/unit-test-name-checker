package com.github.testmethodvalidator.mavenplugin.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidatorPluginUtilsUTest {

    @Test
    public void createClassName_validValues_succeed(){
        assertEquals("com.project.data.SimpleData", ValidatorPluginUtils.createClassName("com.project.data.SimpleDataUTest", null, "UTest"));
        assertEquals("com.project.data.SimpleData", ValidatorPluginUtils.createClassName("com.project.data.SimpleDataUTest", "", "UTest"));
        assertEquals("com.project.data.SimpleData", ValidatorPluginUtils.createClassName("com.project.data.FunctionalSimpleData", "Functional", ""));
        assertEquals("com.project.data.SimpleData", ValidatorPluginUtils.createClassName("com.project.data.FunctionalSimpleData", "Functional", null));
        assertEquals("com.project.data.SimpleData", ValidatorPluginUtils.createClassName("com.project.data.FunctionalSimpleDataUTest", "Functional", "UTest"));

        assertEquals("SimpleData", ValidatorPluginUtils.createClassName("SimpleData", null, null));
        assertEquals("SimpleData", ValidatorPluginUtils.createClassName("SimpleData", "", null));
        assertEquals("SimpleData", ValidatorPluginUtils.createClassName("SimpleData", null, ""));
        assertEquals("SimpleData", ValidatorPluginUtils.createClassName("SimpleDataUTest", null, "UTest"));
        assertEquals("SimpleData", ValidatorPluginUtils.createClassName("SimpleDataUTest", "", "UTest"));
        assertEquals("SimpleData", ValidatorPluginUtils.createClassName("FunctionalSimpleData", "Functional", ""));
        assertEquals("SimpleData", ValidatorPluginUtils.createClassName("FunctionalSimpleData", "Functional", null));
        assertEquals("SimpleData", ValidatorPluginUtils.createClassName("FunctionalSimpleDataUTest", "Functional", "UTest"));
    }
}
