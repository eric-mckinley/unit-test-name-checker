package com.github.testmethodvalidator.mavenplugin.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {

    private final String value;
    private final List<ValidationState> states;

    public ValidationErrors(String value) {
        this.value = value;
        this.states = new ArrayList<ValidationState>();
    }


    public String getValue() {
        return value;
    }

    public List<ValidationState> getStates() {
        return states;
    }

    public void addSuccess(String checkType){
        this.states.add(new ValidationState(checkType, true));
    }

    public void addFailure(String checkType){
        this.states.add(new ValidationState(checkType, false));
    }
}
