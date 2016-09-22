package com.github.testmethodvalidator.mavenplugin.analyse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emckinley on 21/09/2016.
 */
public class UnitTestClassData {

    private final String className;
    private final List<String> testMethods;
    private String actualClassName;
    private final List<String> actualMethods;

    public UnitTestClassData(String className) {
        this.className = className;
        this.testMethods = new ArrayList<String>();
        this.actualMethods = new ArrayList<String>();
    }

    public void addTestMethods(List<String> methodNames){
        this.testMethods.addAll(methodNames);
    }

    public void addActualMethods(List<String> methodNames){
        this.actualMethods.addAll(methodNames);
    }

    public void setActualClassName(String actualClassName) {
        this.actualClassName = actualClassName;
    }

    public String getClassName() {
        return className;
    }

    public List<String> getTestMethods() {
        return testMethods;
    }

    public String getActualClassName() {
        return actualClassName;
    }

    public List<String> getActualMethods() {
        return actualMethods;
    }
}
