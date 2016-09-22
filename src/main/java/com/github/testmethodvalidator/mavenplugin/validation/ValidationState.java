package com.github.testmethodvalidator.mavenplugin.validation;

public class ValidationState {

    private final String checkType;
    private final boolean ok;

    public ValidationState(String checkType, boolean ok) {
        this.checkType = checkType;
        this.ok = ok;
    }

    public String getCheckType() {
        return checkType;
    }

    public boolean isOk() {
        return ok;
    }

    public boolean isNotOk() {
        return !isOk();
    }
}
