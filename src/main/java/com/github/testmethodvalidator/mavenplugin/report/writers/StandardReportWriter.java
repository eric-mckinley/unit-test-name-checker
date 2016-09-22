package com.github.testmethodvalidator.mavenplugin.report.writers;

import com.github.testmethodvalidator.mavenplugin.ValidatorPluginConfig;
import com.github.testmethodvalidator.mavenplugin.report.ReportWriter;
import com.github.testmethodvalidator.mavenplugin.report.TestMethodReport;
import org.apache.maven.doxia.sink.Sink;

import java.util.List;

public class StandardReportWriter implements ReportWriter {
    private int classesPassed;
    private int classesChecked;

    private final Sink sink;
    private final ValidatorPluginConfig config;

    public StandardReportWriter(Sink sink, ValidatorPluginConfig config) {
        this.sink = sink;
        this.config = config;
    }

    public void begin() {
        sink.head();
        sink.title();
        sink.text(config.getProjectName() + " - Test Methods Report");
        sink.title_();
        sink.head_();

        sink.body();

    }

    public void write(String className, List<TestMethodReport> reports) {
        int passedClassMethodsTotal = 0;


        sink.section2();
        sink.sectionTitle2();
        sink.text(config.getProjectName() + " - " + className);
        sink.sectionTitle2_();

        for (TestMethodReport report : reports) {
            sink.table();

            int passed = report.getPassed().size();
            int failed = report.getFailed().size();
            int total = passed + failed;

            sink.tableRow();

            sink.tableHeaderCell();
            sink.bold();
            sink.text("Check Method: " + report.getMethodName());
            sink.bold_();
            sink.tableHeaderCell_();

            sink.tableHeaderCell();
            sink.bold();
            sink.text("Status: " + report.getPassedAsPercentage() + " [" + passed + "/" + total + "]");
            sink.bold_();
            sink.tableHeaderCell_();

            sink.tableRow_();

            List<String> passedList = report.getPassed();
            for (String s : passedList) {
                sink.tableRow();

                sink.tableCell();
                sink.text(s);
                sink.tableCell_();

                sink.tableCell();
                sink.text("Passed");
                sink.tableCell_();

                sink.tableRow_();
            }
            List<String> failedList = report.getFailed();
            for (String s : failedList) {
                sink.tableRow();

                sink.tableCell();
                sink.text(s);
                sink.tableCell_();

                sink.tableCell();
                sink.text("Failed");
                sink.tableCell_();

                sink.tableRow_();
            }

            if (report.hasPassed()) {
                passedClassMethodsTotal++;
            }
            sink.table_();
            sink.lineBreak();
        }
        sink.section2_();

        sink.section2();
        sink.sectionTitle2();
        if (reports.size() > 0) {
            int percentTotal = (passedClassMethodsTotal * 100) / reports.size();
            sink.text("Summary -> " + percentTotal + "% of class methods fully compliant, " + passedClassMethodsTotal + "/" + reports.size());
            if(percentTotal == 100){
                classesPassed++;
            }
        } else {
            sink.text("No Test methods to analyse");
        }
        sink.sectionTitle2_();

        sink.section2_();


        classesChecked++;


    }

    public void close() {

        sink.section1();
        sink.sectionTitle1();
        if (classesChecked > 0) {
            int percentTotal = (classesPassed * 100) / classesChecked;
            sink.text("Final report -> " + percentTotal + "% of test classes fully compliant, " + classesPassed + "/" + classesChecked);
        } else {
            sink.text("Final report -> No test classes to report");
        }
        sink.sectionTitle1_();
        sink.section1_();

        sink.body_();
        sink.flush();
        sink.close();
    }
}
