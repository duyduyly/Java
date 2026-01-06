package org.example.template_method.service;

import org.example.template_method.model.Account;
import org.example.template_method.model.AccountRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class WithdrawalService {


    //if spring boot use @Transactional rollback automatic database changes
    public final void process(AccountRequest request) {
        Long start = System.currentTimeMillis();
        Account account = null;
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        try {
            this.validateRequest(request);
            account = this.validateAndGetAccount(request);
            this.processWithdrawal(account, request);
            this.notifyConsoleNotification(account, request);
            this.notifySMSNotification(account, request);

            executorService.execute(this::handleWithdrawalNotification);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            this.rollback(account, request); //manual rollback
        }finally {
            executorService.shutdown();
            executorService.close();
        }

        Long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start) / 1000 + " seconds");
    }

    protected abstract void validateRequest(AccountRequest request);

    protected abstract Account validateAndGetAccount(AccountRequest request);

    protected abstract void processWithdrawal(Account account, AccountRequest request);

    protected abstract void notifySMSNotification(Account account, AccountRequest request);

    protected abstract void notifyConsoleNotification(Account account, AccountRequest request);

    protected void rollback(Account account, AccountRequest request) {
        System.out.println("Rollbacking withdrawal...");
    }

    protected abstract void handleWithdrawalNotification();
}
