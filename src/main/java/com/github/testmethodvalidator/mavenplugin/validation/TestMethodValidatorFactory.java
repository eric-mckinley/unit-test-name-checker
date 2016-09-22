package com.github.testmethodvalidator.mavenplugin.validation;

import com.github.testmethodvalidator.mavenplugin.ValidatorPluginConfig;
import com.github.testmethodvalidator.mavenplugin.validation.types.ActualMethodExistsTestMethodValidator;
import com.github.testmethodvalidator.mavenplugin.validation.types.CamelCaseTestMethodValidator;
import com.github.testmethodvalidator.mavenplugin.validation.types.InvalidNamesTestMethodValidator;
import com.github.testmethodvalidator.mavenplugin.validation.types.ThreeSegmentTestMethodValidator;
import org.apache.maven.reporting.MavenReportException;

import java.util.ArrayList;
import java.util.List;

public class TestMethodValidatorFactory {

    private final List<TestMethodValidator> validators;

    public TestMethodValidatorFactory(ValidatorPluginConfig config) throws MavenReportException {
        this.validators = new ArrayList<TestMethodValidator>();
        List<String> validatorTypes = config.getValidators();

        if(validatorTypes.contains("all")){
            this.validators.add(new CamelCaseTestMethodValidator());
            this.validators.add(new ThreeSegmentTestMethodValidator());
            this.validators.add(new InvalidNamesTestMethodValidator());
            this.validators.add(new ActualMethodExistsTestMethodValidator());
        }
        else {

            for (int i = 0; i < validatorTypes.size(); i++) {
                String validatorType = validatorTypes.get(i);
                if (validatorType.equals("camel-case")) {
                    this.validators.add(new CamelCaseTestMethodValidator());
                } else if (validatorType.equals("three-segments")) {
                    this.validators.add(new ThreeSegmentTestMethodValidator());
                } else if (validatorType.equals("illegal-prefix")) {
                    this.validators.add(new InvalidNamesTestMethodValidator());
                } else if (validatorType.equals("match-method")) {
                    this.validators.add(new ActualMethodExistsTestMethodValidator());
                }
                else{
                    throw new MavenReportException("Uknown validator type: " + validatorType);
                }
            }
        }
    }

    public List<TestMethodValidator> getValidators(){
        return new ArrayList<TestMethodValidator>(this.validators);
    }
}
