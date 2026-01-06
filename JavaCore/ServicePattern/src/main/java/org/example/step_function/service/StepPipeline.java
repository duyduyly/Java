package org.example.step_function.service;

import org.example.step_function.exception.StepFunctionException;
import org.example.step_function.model.Context;

import java.util.ArrayList;
import java.util.List;

public class StepPipeline<C extends Context> {
    private final List<Step<C>> stepsPipeLine = new ArrayList<>();

    public StepPipeline<C> addStep(Step<C> step) {
        stepsPipeLine.add(step);
        return this;
    }

    public void execute(C context) {
        Long start = System.currentTimeMillis();
        for (Step<C> step : stepsPipeLine) {
            try {
                step.execute(context);
            } catch (StepFunctionException e) {
                System.err.println(e.toMessage());
                this.addErrorChangeLog(context, e);
                step.rollback(context); //manual rollback
                return;
            }
        }

        Long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start) / 1000 + " seconds");
    }

    private void addErrorChangeLog(C context, StepFunctionException e) {
        context.addError(e);
    }
}
