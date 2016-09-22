package com.github.testmethodvalidator.mavenplugin.validation.types;

import com.github.testmethodvalidator.mavenplugin.analyse.UnitTestClassData;
import com.github.testmethodvalidator.mavenplugin.validation.TestMethodValidator;
import com.github.testmethodvalidator.mavenplugin.validation.ValidationErrors;

public abstract class AbstractTestMethodValidator implements TestMethodValidator {

   final public void validate(String method, UnitTestClassData data, ValidationErrors errors) {
        boolean valid = isValid(method, data);
        if(valid){
            errors.addSuccess(getCheckName());
        }
        else{
            errors.addFailure(getCheckName());
        }
    }

    protected abstract boolean isValid(String method, UnitTestClassData data);

    protected abstract String getCheckName();
}
