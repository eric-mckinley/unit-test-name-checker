package com.github.testmethodvalidator.mavenplugin.validation.types;

import com.github.testmethodvalidator.mavenplugin.analyse.UnitTestClassData;

public class ThreeSegmentTestMethodValidator extends AbstractTestMethodValidator {

    public static final String NAME = "Test Method name format is part1_part2_part3";

    @Override
    protected boolean isValid(String method, UnitTestClassData data) {
        String[] segments = method.split("_");
        return segments.length == 3;
    }

    @Override
    protected String getCheckName() {
        return NAME;
    }
}
