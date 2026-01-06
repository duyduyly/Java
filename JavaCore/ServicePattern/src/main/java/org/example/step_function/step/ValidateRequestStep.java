package org.example.step_function.step;

import org.example.step_function.model.AccountContext;
import org.example.step_function.service.Step;
import org.example.step_function.exception.StepFunctionException;
import org.example.step_function.model.AccountRequest;

import java.util.Objects;

public class ValidateRequestStep implements Step<AccountContext> {

    @Override
    public void execute(AccountContext ctx) {
        try {
            System.out.println("Validate Request Processing...");
            Thread.sleep(2000);// Simulate delay for processing notice

            ctx.setStep("ValidateRequest");
            AccountRequest request = ctx.getAccountRequest();
            this.validate(request);

            ctx.setValidated(true);
            ctx.addChangeLog("Execute Validate Information Step", null);

            System.out.println("Validation Request Processed.");
        } catch (Exception e) {
            throw new StepFunctionException("", "Validation Request failed", e);
        }
    }


    @Override
    public void rollback(AccountContext context) {
        System.err.println("Manual Rollback.");
    }

    private void validate(AccountRequest request) {
        if (Objects.isNull(request)) {
            throw new StepFunctionException("", "Account not found", null);
        }

        if (Objects.isNull(request.getAccountNumber())) {
            throw new StepFunctionException("", "Account information mismatch", null);
        }

        if (Objects.isNull(request.getAmount()) || request.getAmount() <= 0) {
            throw new StepFunctionException("", "Withdrawal amount invalid", null);
        }
    }

}


