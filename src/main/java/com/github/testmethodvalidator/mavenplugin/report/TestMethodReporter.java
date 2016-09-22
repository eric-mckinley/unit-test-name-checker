package com.github.testmethodvalidator.mavenplugin.report;

import com.github.testmethodvalidator.mavenplugin.ValidatorPluginConfig;
import com.github.testmethodvalidator.mavenplugin.analyse.UnitTestClassData;
import com.github.testmethodvalidator.mavenplugin.validation.TestMethodValidator;
import com.github.testmethodvalidator.mavenplugin.validation.TestMethodValidatorFactory;
import com.github.testmethodvalidator.mavenplugin.validation.ValidationErrors;
import org.apache.maven.reporting.MavenReportException;

import java.util.ArrayList;
import java.util.List;

public class TestMethodReporter {

    private final TestMethodValidatorFactory validatorFactory;

    public TestMethodReporter(ValidatorPluginConfig config) throws MavenReportException {
        validatorFactory = new TestMethodValidatorFactory(config);
    }


    public List<TestMethodReport> createReports(UnitTestClassData data){
        List<TestMethodReport> classMethodReports = new ArrayList<TestMethodReport>();
        List<String> unitTestMethods=  data.getTestMethods();
        for(String unitTestMethod:  unitTestMethods){
            classMethodReports.add(createReport(unitTestMethod, data));
        }
        return classMethodReports;
    }

    private TestMethodReport createReport(String method, UnitTestClassData data){
        ValidationErrors errors = new ValidationErrors(method);

        List<TestMethodValidator> validators = validatorFactory.getValidators();
        for (TestMethodValidator validator : validators) {
            validator.validate(method,data, errors);
        }
        return new TestMethodReport(method, errors);
    }
}
