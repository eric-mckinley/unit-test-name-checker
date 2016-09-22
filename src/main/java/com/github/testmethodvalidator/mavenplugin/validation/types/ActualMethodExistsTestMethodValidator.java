package com.github.testmethodvalidator.mavenplugin.validation.types;

import com.github.testmethodvalidator.mavenplugin.analyse.UnitTestClassData;

/**
 * Created by emckinley on 21/09/2016.
 */
public class ActualMethodExistsTestMethodValidator extends AbstractTestMethodValidator {

    public static final String NAME = "Test Method name matches method in src/main/java";

    @Override
    protected boolean isValid(String method, UnitTestClassData data) {
        if(data.getActualClassName() != null){
            String[] segments=  method.split("_");
            return data.getActualMethods().contains(segments[0]);
        }
        else{
            return false;
        }
    }

    @Override
    protected String getCheckName() {
        return NAME;
    }
}
