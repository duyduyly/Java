package org.example.step_function.model;

import lombok.Getter;
import org.example.step_function.exception.StepFunctionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Context {
    @Getter
    private List<String> changeLog;

    public void addChangeLog(String step ,String message, Object data) {
        StringBuilder builder = new StringBuilder();
        builder.append("Step(").append(step).append(")");
        if (Objects.nonNull(message)) builder.append(" - ").append(message);
        if (Objects.nonNull(data)) builder.append(" - ").append(data);
        builder.append(".");

        if (Objects.isNull(changeLog)) changeLog = new ArrayList<>();
        changeLog.add(builder.toString());
    }

    public void addError(StepFunctionException e) {
        if (Objects.isNull(changeLog)) changeLog = new ArrayList<>();
        changeLog.add("Error: " + e.toMessage());
    }
}
