package com.github.testmethodvalidator.mavenplugin.report;

import java.util.List;

public interface ReportWriter {

    public void begin();

    public void write(String className, List<TestMethodReport> reports);

    public void close();
}
