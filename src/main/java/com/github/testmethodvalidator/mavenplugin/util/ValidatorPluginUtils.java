package com.github.testmethodvalidator.mavenplugin.util;

public class ValidatorPluginUtils {

    static public String createClassName(String testClassName, String prefix, String suffix) {
        String packageName;
        String simpleClassName;
        int lastDotIndex = testClassName.lastIndexOf(".");
        if (lastDotIndex > 0) {
            packageName = testClassName.substring(0, lastDotIndex + 1);
            simpleClassName = testClassName.substring(lastDotIndex +1, testClassName.length());
        } else {
            packageName = "";
            simpleClassName = testClassName;
        }
        if (prefix != null) {
            if (simpleClassName.startsWith(prefix)) {
                simpleClassName = simpleClassName.substring(prefix.length());
            }
            else{
                return null;
            }
        }
        if (suffix != null) {
            if (simpleClassName.endsWith(suffix)) {
                simpleClassName = simpleClassName.substring(0, simpleClassName.length() - suffix.length());
            }
            else{
                return null;
            }
        }
        return packageName + simpleClassName;
    }
}
