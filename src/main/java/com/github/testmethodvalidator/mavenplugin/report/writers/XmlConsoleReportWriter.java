package com.github.testmethodvalidator.mavenplugin.report.writers;

import com.github.testmethodvalidator.mavenplugin.report.ReportWriter;
import com.github.testmethodvalidator.mavenplugin.report.TestMethodReport;
import org.apache.maven.plugin.logging.Log;

import java.util.List;

public class XmlConsoleReportWriter implements ReportWriter {

    private final StringBuilder builder;
    private final Log log;
    private final boolean formatXml;
    private int tabIndent;

    public XmlConsoleReportWriter(Log log, boolean formatXml) {
        this.builder = new StringBuilder();
        this.log = log;
        this.formatXml = formatXml;
    }

    public void begin() {
        open("reports");
    }

    public void write(String className, List<TestMethodReport> reports) {

        open("report");
        value("className", className);
        open("testMethods");

        int passedClassTotal = 0;
        int passedMethodTotal = 0;
        int methodTotal = 0;

        for (TestMethodReport report : reports) {
            open("testMethod");

            value("name", report.getMethodName());


            int passed = report.getPassed().size();
            int failed = report.getFailed().size();
            int total = passed + failed;

            passedMethodTotal += passed;
            methodTotal += total;

            open("passed");
            List<String> passedList = report.getPassed();
            for (String s : passedList) {
                value("value", s);
            }
            close("passed");

            open("failed");
            List<String> failedList = report.getFailed();
            for (String s : failedList) {
                value("value", s);
            }
            close("failed");

            if (report.hasPassed()) {
                passedClassTotal++;
            }

            open("result");

            value("passed", String.valueOf(passed));
            value("failed", String.valueOf(failed));
            value("total", String.valueOf(total));

            close("result");

            close("testMethod");
        }

        open("results");

        open("methods");
        value("passed", String.valueOf(passedMethodTotal));
        value("failed", String.valueOf(methodTotal - passedMethodTotal));
        value("total", String.valueOf(methodTotal));
        close("methods");

        open("classes");
        value("passed", String.valueOf(passedClassTotal));
        value("failed", String.valueOf(reports.size() - passedClassTotal));
        value("total", String.valueOf(reports.size()));
        close("classes");


        if (reports.size() > 0) {
            int percentTotal = (passedClassTotal * 100) / reports.size();
            value("percentage", String.valueOf(percentTotal));
        } else {
            value("percentage", String.valueOf("NA"));
        }
        close("results");


        close("testMethods");
        close("report");
    }

    public void close() {
        close("reports");
        log.info("XML Report\n\n" + builder.toString() + "\n\n");
    }


    private void value(String tag, String value) {
        writeXml("<" + tag + ">" + value + "</" + tag + ">");
    }

    private void open(String tag) {
        writeXml("<" + tag + ">");
        if (formatXml) {
            tabIndent++;
        }
    }

    private void close(String tag) {
        if (formatXml) {
            tabIndent--;
        }
        writeXml("</" + tag + ">");
    }

    private void writeXml(String xml) {
        for (int i = 0; i < tabIndent; i++) {
            builder.append("\t");
        }
        builder.append(xml);
        if (formatXml) {
            builder.append("\n");
        }
    }
}
