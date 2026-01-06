package org.example.step_function.step;

import org.example.step_function.model.Account;
import org.example.step_function.model.AccountContext;
import org.example.step_function.service.Step;
import org.example.step_function.exception.StepFunctionException;
import org.example.step_function.model.AccountRequest;

import java.util.List;
import java.util.Objects;

public class ValidateAndGetAccountStep implements Step<AccountContext> {

    @Override
    public void execute(AccountContext ctx) {
        try {
            System.out.println("Validate Account Processing...");
            Thread.sleep(2000);// Simulate delay for processing notice

            ctx.setStep("ValidateAccount");

            AccountRequest request = ctx.getAccountRequest();
            Account account = this.getAccount(request);
            this.checkAccountExists(account);
            this.checkAccountIsActive(account);
            this.checkPin(account, request);
            this.checkLimit(account, request);
            this.checkBalance(account, request);
            ctx.setAccount(account);
            ctx.addChangeLog("Account validated.", "Account: " + account.getAccountNumber());

            ctx.setValidated(true);
            ctx.addChangeLog("Execute Validate Balance Step", null);

            System.out.println("Validation Account Processed.");
        } catch (Exception e) {
            throw new StepFunctionException("", "Validation And Get failed", e);
        }
    }

    @Override
    public void rollback(AccountContext context) {
        System.err.println("Manual Rollback.");
    }

    private Account getAccount(AccountRequest request) {
        List<Account> accounts = Account.mockData();
        return accounts.stream().filter(ac -> Objects.equals(ac.getAccountNumber(), request.getAccountNumber())).findFirst().orElse(new Account());
    }

    private void checkAccountExists(Account account) {
        if (Objects.isNull(account) || Objects.isNull(account.getAccountNumber())) {
            throw new StepFunctionException("", "Account not found", null);
        }
    }

    private void checkAccountIsActive(Account account) {
        if (!account.getActive()) {
            throw new StepFunctionException("", "Account is inactive", null);
        }
    }

    private void checkPin(Account account, AccountRequest request) {
        if (!Objects.equals(account.getPin(), request.getPin())) {
            throw new StepFunctionException("", "Pin is incorrect", null);
        }
    }

    private void checkLimit(Account account, AccountRequest request) {
        if (request.getAmount() > account.getWithdrawalLimit()) {
            throw new StepFunctionException("", "Withdrawal amount exceeds limit", null);
        }
    }

    private void checkBalance(Account account, AccountRequest request) {
        if (request.getAmount() > account.getBalance()) {
            throw new StepFunctionException("", "Insufficient Balance", null);
        }
    }
}


