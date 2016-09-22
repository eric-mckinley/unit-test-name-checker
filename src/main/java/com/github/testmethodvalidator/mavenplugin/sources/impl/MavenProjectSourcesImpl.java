package com.github.testmethodvalidator.mavenplugin.sources.impl;

import com.github.testmethodvalidator.mavenplugin.sources.MavenProjectSources;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import org.apache.maven.reporting.MavenReportException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emckinley on 20/09/2016.
 */
public class MavenProjectSourcesImpl implements MavenProjectSources {

    private final List<String> classNames;
    private final ClassPool pool;

    public MavenProjectSourcesImpl(List<String> classPathElements) throws MavenReportException {
        this.pool = new ClassPool();
        this.classNames = new ArrayList<String>();

        for (String element : classPathElements) {
            try {
                pool.insertClassPath(element);
                loadClassNames(element, new File(element), classNames);
            } catch (NotFoundException e) {
                throw new MavenReportException("Cannot create sources: " + element, e);
            }
        }
    }


    public List<String> getClassesWithMethodAnnotation(Class annotation) throws MavenReportException {
        List<String> annotatedClasses = new ArrayList<String>();
        for (String className : classNames) {
            CtClass cc = getClass(className);
            CtMethod[] methods = cc.getMethods();
            for (CtMethod method : methods) {
                if (method.hasAnnotation(annotation)) {
                    annotatedClasses.add(className);
                    break;
                }
            }
        }
        return annotatedClasses;
    }

    public List<String> getMethodsWithAnnotation(String className, Class annotation) throws MavenReportException {
        List<String> annotatedMethods = new ArrayList<String>();
        CtClass cc = getClass(className);
        CtMethod[] methods = cc.getMethods();
        for (CtMethod method : methods) {
            if (method.hasAnnotation(annotation)) {
                annotatedMethods.add(method.getName());
            }
        }
        return annotatedMethods;
    }

    public List<String> getMethods(String className) throws MavenReportException {
        List<String> methodNames = new ArrayList<String>();
        CtClass cc = getClass(className);
        CtMethod[] methods = cc.getMethods();
        for (CtMethod method : methods) {
            methodNames.add(method.getName());
        }
        return methodNames;
    }

    public boolean classExists(String className) {
        try {
            CtClass cc = getClass(className);
            ClassFile cf = cc.getClassFile2();
            return cf != null;
        } catch (Exception e) {
            return false;
        }
    }

    private CtClass getClass(String className) throws MavenReportException {
        try {
            return pool.get(className);
        } catch (NotFoundException e) {
            throw new MavenReportException("Cannot find class: " + className, e);
        }
    }

    private void loadClassNames(String root, File folder, List<String> classNames) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            for (File file : files) {
                String fileName = file.getAbsolutePath();
                if (file.isDirectory()) {
                    loadClassNames(root, file, classNames);
                } else if (fileName.endsWith(".class")) {
                    fileName = fileName.replace(root + "/", "");
                    fileName = fileName.replace(".class", "");
                    fileName = fileName.replace("/", ".");
                    classNames.add(fileName);
                }
            }
        }
    }
}
