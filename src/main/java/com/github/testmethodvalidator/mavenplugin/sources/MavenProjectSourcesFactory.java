package com.github.testmethodvalidator.mavenplugin.sources;

import com.github.testmethodvalidator.mavenplugin.sources.impl.MavenProjectSourcesImpl;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.MavenReportException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emckinley on 20/09/2016.
 */
public class MavenProjectSourcesFactory {

    private final MavenProject project;

    public MavenProjectSourcesFactory(MavenProject project) {
        this.project = project;
    }

    public MavenProjectSources getSources(SourcesType type) throws MavenReportException {
        try {
            switch (type) {
                case MAIN:
                    List fullList  = new ArrayList();
                    fullList.addAll(project.getCompileClasspathElements());
                    fullList.addAll(project.getCompileSourceRoots());
                    fullList.addAll(project.getCompileDependencies());
                    return new MavenProjectSourcesImpl(fullList);
                case TEST:
                    return new MavenProjectSourcesImpl(project.getTestClasspathElements());
                default:
                    throw new IllegalArgumentException("Unsupported sources type: " + type);
            }
        } catch (DependencyResolutionRequiredException e) {
            throw new MavenReportException("Cannot create sources: " + e.getMessage(), e);
        }
    }
}
