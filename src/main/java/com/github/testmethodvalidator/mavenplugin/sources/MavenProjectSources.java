package com.github.testmethodvalidator.mavenplugin.sources;

import org.apache.maven.reporting.MavenReportException;

import java.util.List;

public interface MavenProjectSources {

    public boolean classExists(String className);

    public List<String> getClassesWithMethodAnnotation(Class annotation) throws MavenReportException;

    public List<String> getMethodsWithAnnotation(String className, Class annotation) throws MavenReportException;

    public List<String> getMethods(String className) throws MavenReportException;

}
