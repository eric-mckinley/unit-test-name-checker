package com.github.testmethodvalidator.mavenplugin;

import java.util.List;

/**
 * Created by emckinley on 21/09/2016.
 */
public class ValidatorPluginConfig {

    private String testClassNamePrefix;
    private String testClassNameSuffix;
    private String projectName;
    private String scanPackage;
    private boolean formatXml;
    private List<String> validators;

    public List<String> getValidators() {
        return validators;
    }

    public void setValidators(List<String> validators) {
        this.validators = validators;
    }

    public boolean isFormatXml() {
        return formatXml;
    }

    public void setFormatXml(boolean formatXml) {
        this.formatXml = formatXml;
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTestClassNamePrefix() {
        return testClassNamePrefix;
    }

    public void setTestClassNamePrefix(String testClassNamePrefix) {
        this.testClassNamePrefix = testClassNamePrefix;
    }

    public String getTestClassNameSuffix() {
        return testClassNameSuffix;
    }

    public void setTestClassNameSuffix(String testClassNameSuffix) {
        this.testClassNameSuffix = testClassNameSuffix;
    }
}
