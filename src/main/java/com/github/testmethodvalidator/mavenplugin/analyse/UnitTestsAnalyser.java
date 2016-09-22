package com.github.testmethodvalidator.mavenplugin.analyse;

import com.github.testmethodvalidator.mavenplugin.ValidatorPluginConfig;
import com.github.testmethodvalidator.mavenplugin.sources.MavenProjectSources;
import com.github.testmethodvalidator.mavenplugin.util.ValidatorPluginUtils;
import org.apache.maven.reporting.MavenReportException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UnitTestsAnalyser {

    private final ValidatorPluginConfig config;
    private final MavenProjectSources testSources;
    private final MavenProjectSources mainSources;

    public UnitTestsAnalyser(MavenProjectSources testSources, MavenProjectSources mainSources, ValidatorPluginConfig config) {
        this.testSources = testSources;
        this.mainSources = mainSources;
        this.config = config;
    }

    public List<UnitTestClassData> analyse() throws MavenReportException {

        List<UnitTestClassData> testClassDataList = new ArrayList<UnitTestClassData>();

        List<String> testClassNames = filterByScanPackage(testSources.getClassesWithMethodAnnotation(Test.class));
        for (String testClassName : testClassNames) {
            UnitTestClassData data = new UnitTestClassData(testClassName);
            data.addTestMethods(testSources.getMethodsWithAnnotation(testClassName, Test.class));

            String actualClassName = ValidatorPluginUtils.createClassName(testClassName, config.getTestClassNamePrefix(), config.getTestClassNameSuffix());

            if (mainSources.classExists(actualClassName)) {
                data.setActualClassName(actualClassName);
                data.addActualMethods(mainSources.getMethods(actualClassName));
            }
            testClassDataList.add(data);
        }
        return testClassDataList;
    }

    private List<String> filterByScanPackage(List<String> testClassNames) {
        if (config.getScanPackage() != null) {
            List<String> filtered = new ArrayList<String>();

            for (String testClass : testClassNames) {
                if (testClass.startsWith(config.getScanPackage())) {
                    filtered.add(testClass);
                }
            }
            return filtered;
        } else {
            return testClassNames;
        }

    }
}
