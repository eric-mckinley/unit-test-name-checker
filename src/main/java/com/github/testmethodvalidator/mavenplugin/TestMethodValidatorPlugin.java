package com.github.testmethodvalidator.mavenplugin;

import com.github.testmethodvalidator.mavenplugin.analyse.UnitTestClassData;
import com.github.testmethodvalidator.mavenplugin.analyse.UnitTestsAnalyser;
import com.github.testmethodvalidator.mavenplugin.report.ReportWriter;
import com.github.testmethodvalidator.mavenplugin.report.ReportWriterFactory;
import com.github.testmethodvalidator.mavenplugin.report.TestMethodReport;
import com.github.testmethodvalidator.mavenplugin.report.TestMethodReporter;
import com.github.testmethodvalidator.mavenplugin.report.writers.StandardReportWriter;
import com.github.testmethodvalidator.mavenplugin.sources.MavenProjectSourcesFactory;
import com.github.testmethodvalidator.mavenplugin.sources.SourcesType;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Mojo(name = "methods")
public class TestMethodValidatorPlugin extends AbstractMavenReport {

    @Parameter(defaultValue = "")
    private String testClassNamePrefix;

    @Parameter(defaultValue = "UTest")
    private String testClassNameSuffix;

    @Parameter
    private String scanPackage;

    @Parameter
    private String testReportName;

    @Parameter
    private String[] consoleOutputs;

    @Parameter
    private String[] validators;

    private ValidatorPluginConfig config;

    private List<ReportWriter> reportWriters;

    public String getOutputName() {

        if (testReportName != null && testReportName.length() > 0) {
            return testReportName;
        } else {
            return "test-methods";
        }
    }

    public String getName(Locale locale) {
        return "Unit Test Name Compliance";
    }

    public String getDescription(Locale locale) {
        return "Unit Test Method Names Validator";
    }

    @Override
    protected void executeReport(Locale locale) throws MavenReportException {

        initConfig();

        MavenProjectSourcesFactory sourcesFactory = new MavenProjectSourcesFactory(project);

        UnitTestsAnalyser analyser = new UnitTestsAnalyser(
                sourcesFactory.getSources(SourcesType.TEST),
                sourcesFactory.getSources(SourcesType.MAIN),
                this.config);

        List<UnitTestClassData> dataList = analyser.analyse();
        TestMethodReporter reporter = new TestMethodReporter(this.config);

        for (ReportWriter writer : reportWriters) {
            writer.begin();
        }

        for (UnitTestClassData data : dataList) {
            List<TestMethodReport> reports = reporter.createReports(data);
            for (ReportWriter writer : reportWriters) {
                writer.write(data.getClassName(), reports);
            }
        }
        for (ReportWriter writer : reportWriters) {
            writer.close();
        }
    }


    private void initConfig() throws MavenReportException {
        if (this.config == null) {
            this.config = new ValidatorPluginConfig();
            this.config.setTestClassNamePrefix(testClassNamePrefix);
            this.config.setTestClassNameSuffix(testClassNameSuffix);
            this.config.setProjectName(project.getName());
            this.config.setScanPackage(scanPackage);

            if (this.validators == null || this.validators.length == 0) {
                throw new MavenReportException("At least one validator must be specified");
            }
            this.config.setValidators(Arrays.asList(this.validators));


            if (consoleOutputs == null) {
                consoleOutputs = new String[0];
            }

            ReportWriterFactory writerFactory = new ReportWriterFactory(getLog(), this.config);

            reportWriters = new ArrayList<ReportWriter>();
            reportWriters.add(new StandardReportWriter(getSink(), config));
            for (String consoleOutput : consoleOutputs) {
                reportWriters.add(writerFactory.getWriter(consoleOutput));
            }
        }
    }
}
