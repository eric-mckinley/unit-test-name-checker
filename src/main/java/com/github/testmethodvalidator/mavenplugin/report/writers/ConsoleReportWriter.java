package com.github.testmethodvalidator.mavenplugin.report.writers;

import com.github.testmethodvalidator.mavenplugin.ValidatorPluginConfig;
import com.github.testmethodvalidator.mavenplugin.report.ReportWriter;
import com.github.testmethodvalidator.mavenplugin.report.TestMethodReport;
import org.apache.maven.plugin.logging.Log;

import java.util.List;

public class ConsoleReportWriter implements ReportWriter {

    public static final String LINE = "--------------------------------------------------------------------------------";

    private int classesPassed;
    private int classesChecked;

    private final Log log;
    private final ValidatorPluginConfig config;

    public ConsoleReportWriter(ValidatorPluginConfig config, Log log) {
        this.config = config;
        this.log = log;
    }

    public void begin() {
        log.info("Plugin Test Method Name");
    }

    public void write(String className, List<TestMethodReport> reports) {
        log.info(LINE);
        log.info("CHECK UNIT TEST METHOD NAMES - " + config.getProjectName() + " - " + className);
        log.info(LINE);
        int passedClassMethodsTotal = 0;

        for (TestMethodReport report : reports) {
            int passed = report.getPassed().size();
            int failed = report.getFailed().size();
            int total = passed + failed;


            log.info("Method " + report.getMethodName() + " passed " + report.getPassedAsPercentage() + ", " + passed + "/" + total);
            List<String> passedList = report.getPassed();
            for (String s : passedList) {
                log.info("Passed -> " + s);
            }
            List<String> failedList = report.getFailed();
            for (String s : failedList) {
                log.info("Failed -> " + s);
            }

            if (report.hasPassed()) {
                passedClassMethodsTotal++;
            }
            log.info("");
        }

        if (reports.size() > 0) {
            int percentTotal = (passedClassMethodsTotal * 100) / reports.size();
            log.info("Summary -> " + percentTotal + "% of class methods fully compliant, " + passedClassMethodsTotal + "/" + reports.size());
            if(percentTotal == 100){
                classesPassed++;
            }
        } else {
            log.info("No Test methods to analyse");
        }
        classesChecked++;
        log.info("");
    }

    public void close() {
        log.info(LINE);
        log.info(LINE);

        if (classesChecked > 0) {
            int percentTotal = (classesPassed * 100) / classesChecked;
            log.info("Final report -> " + percentTotal + "% of test classes fully compliant, " + classesPassed + "/" + classesChecked);
        } else {
            log.info("Final report -> No test classes to report");
        }
        log.info(LINE);
        log.info(LINE);
    }

}
