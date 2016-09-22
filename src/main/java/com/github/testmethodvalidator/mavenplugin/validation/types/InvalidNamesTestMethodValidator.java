package com.github.testmethodvalidator.mavenplugin.validation.types;

import com.github.testmethodvalidator.mavenplugin.analyse.UnitTestClassData;

/**
 * Created by emckinley on 21/09/2016.
 */
public class InvalidNamesTestMethodValidator extends AbstractTestMethodValidator {

    public static final String NAME = "Test Method does not start with invalid prefix";
    public static final String [] INVALID_NAMES = new String[]{
            "test", "verify", "check", "assert"
    };


    @Override
    protected boolean isValid(String method, UnitTestClassData data) {
        String firstSegment = method.split("_")[0];
        if(!data.getActualMethods().contains(firstSegment)){
            for(String invalidName: INVALID_NAMES){
                if(firstSegment.startsWith(invalidName)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected String getCheckName() {
        return NAME;
    }
}
