package org.example.step_function.step;

import org.example.step_function.constant.Constant;
import org.example.step_function.exception.StepFunctionException;
import org.example.step_function.model.AccountContext;
import org.example.step_function.service.Step;
import org.example.step_function.model.Account;
import org.example.step_function.model.AccountRequest;

public class ProcessingWithdrawalStep implements Step<AccountContext> {

    @Override
    public void execute(AccountContext ctx){
        try{
            System.out.println("Processing Withdrawal Processing...");
            Thread.sleep(2000);// Simulate delay for processing notice

            ctx.setStep("ProcessingWithdrawal");

            Account account = ctx.getAccount();
            AccountRequest request = ctx.getAccountRequest();
            this.deductBalance(account, request, ctx);
            this.deductFee(account, ctx);

            ctx.setDeductedAmount(true);

            System.out.println("Processing Withdrawal Processed.");
        } catch (Exception e) {
            throw new StepFunctionException("", "Processing Withdrawal failed", e);
        }
    }

    @Override
    public void rollback(AccountContext context) {
        System.err.println("Manual Rollback.");
    }

    private void deductBalance(Account account, AccountRequest request, AccountContext ctx){
        Double newBalance = account.getBalance() - request.getAmount();
        account.setBalance(newBalance);
        ctx.addChangeLog("Balance deducted.", "New Balance: " + newBalance);
    }

    private void deductFee(Account account, AccountContext ctx){
        Double newBalance = account.getBalance() - Constant.FEE;
        account.setBalance(newBalance);
        ctx.addChangeLog("Fee deducted.", "New Balance: " + newBalance);
    }
}
