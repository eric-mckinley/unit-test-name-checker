package com.github.testmethodvalidator.mavenplugin.report;

import com.github.testmethodvalidator.mavenplugin.ValidatorPluginConfig;
import com.github.testmethodvalidator.mavenplugin.report.writers.ConsoleReportWriter;
import com.github.testmethodvalidator.mavenplugin.report.writers.XmlConsoleReportWriter;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.reporting.MavenReportException;

public class ReportWriterFactory {

    private final Log log;
    private final ValidatorPluginConfig config;

    public ReportWriterFactory(Log log, ValidatorPluginConfig config) {
        this.log = log;
        this.config = config;
    }

    public ReportWriter getWriter(String reportType) throws MavenReportException {
        if(reportType.equals("console")){
            return new ConsoleReportWriter(config, log);
        }
        else if(reportType.equals("xml")){
            return new XmlConsoleReportWriter(log, true);
        }
        throw new MavenReportException("Invalid reportType");
    }
}
