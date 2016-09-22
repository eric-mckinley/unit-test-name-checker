package com.github.testmethodvalidator.mavenplugin.report;

import com.github.testmethodvalidator.mavenplugin.validation.ValidationErrors;
import com.github.testmethodvalidator.mavenplugin.validation.ValidationState;

import java.util.ArrayList;
import java.util.List;

public class TestMethodReport {

    private final String methodName;
    private final ValidationErrors errors;

    public TestMethodReport(String methodName, ValidationErrors errors) {
        this.methodName = methodName;
        this.errors = errors;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean hasPassed(){
        return getPassed().size() > 0 && getFailed().size() == 0;
    }

    public boolean hasFailed(){
        return getFailed().size() > 0;
    }

    public List<String> getPassed(){
        return filterByState(true);
    }

    public List<String> getFailed() {
        return filterByState(false);
    }

    private List<String> filterByState(boolean state){
        List<String> filteredList = new ArrayList<String>();
        List<ValidationState> states = errors.getStates();
        for (int i = 0; i < states.size(); i++) {
            ValidationState validationState = states.get(i);
            if(validationState.isOk() == state){
                filteredList.add(validationState.getCheckType());
            }
        }
        return filteredList;
    }

    public String getPassedAsPercentage(){
        return getAsPercentage(getPassed());
    }

    public String getFailedAsPercentage(){
        return getAsPercentage(getFailed());
    }

    private String getAsPercentage(List<String> list){
        int totalTests = errors.getStates().size();
        if(totalTests >0){
            int count = list.size();
            return (count * 100) / totalTests + "%";
        }
        else{
            return "No Data";
        }

    }
}
