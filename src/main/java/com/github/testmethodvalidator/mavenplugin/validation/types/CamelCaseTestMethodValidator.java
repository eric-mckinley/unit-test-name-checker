package com.github.testmethodvalidator.mavenplugin.validation.types;

import com.github.testmethodvalidator.mavenplugin.analyse.UnitTestClassData;

/**
 * Created by emckinley on 21/09/2016.
 */
public class CamelCaseTestMethodValidator extends AbstractTestMethodValidator {

    public static final String NAME = "Test Method segments are camel case";

    @Override
    protected boolean isValid(String method, UnitTestClassData data) {
        String[] segments = method.split("_");
        for(String s: segments){
            if(isNotCamelCase(s)){
                return false;
            }
        }
        return true;
    }

    private boolean isNotCamelCase(String s){
        return !isCamelCase(s);
    }

    private boolean isCamelCase(String s){
        String firstLetter = s.substring(0,1);
        return firstLetter.toLowerCase().equals(firstLetter);
    }

    @Override
    protected String getCheckName() {
        return NAME;
    }
}
