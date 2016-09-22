package com.github.testmethodvalidator.mavenplugin.validation;

import com.github.testmethodvalidator.mavenplugin.analyse.UnitTestClassData;

public interface TestMethodValidator {

    public void validate(String method, UnitTestClassData data, ValidationErrors errors);
}
