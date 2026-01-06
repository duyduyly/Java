package org.example.template_method.service.impl;

import com.google.gson.Gson;
import org.example.template_method.constant.Constant;
import org.example.template_method.exception.StepFunctionException;
import org.example.template_method.model.Account;
import org.example.template_method.model.AccountEvent;
import org.example.template_method.model.AccountRequest;
import org.example.template_method.model.enums.AccountEventEnums;
import org.example.template_method.service.WithdrawalService;
import org.example.template_method.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class WithdrawalServiceImpl extends WithdrawalService {

    private final Gson gson = new Gson();

    @Override
    protected void validateRequest(AccountRequest request) {
        try {
            System.out.println("Validate Request Processing...");
            Thread.sleep(2000);// Simulate delay for processing notice

            this.validate(request);

            System.out.println("Validation Request Processed.");
        } catch (Exception e) {
            throw new StepFunctionException("", "Validation Request failed", e);
        }
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

    @Override
    protected Account validateAndGetAccount(AccountRequest request) {
        try {
            System.out.println("Validate Account Processing...");
            Thread.sleep(2000);// Simulate delay for processing notice

            Account account = this.getAccount(request);
            this.checkAccountExists(account);
            this.checkAccountIsActive(account);
            this.checkPin(account, request);
            this.checkLimit(account, request);
            this.checkBalance(account, request);
            System.out.println("Validate And Get Account Processed.");
            return account;
        } catch (Exception e) {
            throw new StepFunctionException("", "Validation And Get failed", e);
        }
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

    @Override
    protected void processWithdrawal(Account account, AccountRequest request) {
        try {
            System.out.println("Processing Withdrawal Processing...");
            Thread.sleep(2000);// Simulate delay for processing notice

            this.deductBalance(account, request);
            this.deductFee(account);

            System.out.println("Processing Withdrawal Processed.");
        } catch (Exception e) {
            throw new StepFunctionException("", "Processing Withdrawal failed", e);
        }
    }

    private void deductBalance(Account account, AccountRequest request) {
        Double newBalance = account.getBalance() - request.getAmount();
        account.setBalance(newBalance);
    }

    private void deductFee(Account account) {
        Double newBalance = account.getBalance() - Constant.FEE;
        account.setBalance(newBalance);
    }


    @Override
    protected void notifySMSNotification(Account account, AccountRequest request) {
        try {
            System.out.println("SMS Notice Processing...");
            this.pushMessage("SMS Notice", AccountEventEnums.SMS, account);
        } catch (Exception e) {
            throw new StepFunctionException("", "SMS Notification failed", e);
        }
    }

    @Override
    protected void notifyConsoleNotification(Account account, AccountRequest request) {
        try {
            System.out.println("Console Notice Processing...");

            StringBuilder builder = new StringBuilder();
            builder.append("\n").append("Notification: ").append("\n");
            builder.append("===================================").append("\n");
            builder.append("Name: ").append(account.getName()).append("\n");
            builder.append("Account Number: ").append(account.getAccountNumber()).append("\n");
            builder.append("Before Balance: ").append(request.getAmount() + account.getBalance()).append("\n");
            builder.append("Withdrawal Amount: ").append(request.getAmount()).append("\n");
            builder.append("Balance: ").append(account.getBalance()).append("\n");
            builder.append("Withdrawal Successfully.");
            builder.append("\n").append("===================================").append("\n");

            this.pushMessage(builder.toString(), AccountEventEnums.CONSOLE, account);
        } catch (Exception e) {
            throw new StepFunctionException("", "Console Notification failed", e);
        }
    }

    private void pushMessage(String message, AccountEventEnums eventType, Account account) throws IOException, TimeoutException {
        RabbitMQUtils rabbitMQUtils = new RabbitMQUtils(Constant.QUEUE_NAME);
        AccountEvent accountEvent = AccountEvent.builder()
                .eventType(eventType)
                .accountNumber(account.getAccountNumber())
                .message(message)
                .status("SUCCESS")
                .build();

        rabbitMQUtils.sendMessage(gson.toJson(accountEvent));
        System.out.println("Pushed SMS Notification Queue.");
    }

    @Override
    protected void handleWithdrawalNotification() {
        try {
            RabbitMQUtils rabbitMQUtils = new RabbitMQUtils(Constant.QUEUE_NAME);
            rabbitMQUtils.receiveMessage((message) -> {
                AccountEvent accountEvent = gson.fromJson(message, AccountEvent.class);

                switch (accountEvent.getEventType()) {
                    case CONSOLE -> {
                        try {
                            this.noticeConsole(accountEvent);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case SMS -> {
                        try {
                            this.noticeSMS(accountEvent);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    default -> System.err.println("Event Does Not Exist.");
                }
            });

        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private void noticeSMS(AccountEvent accountEvent) throws InterruptedException {
        System.out.println("Notification SMS Processing...");
        Thread.sleep(2000);// Simulate delay for processing notice
        System.out.println(accountEvent.getMessage());
        System.out.println("Notification SMS Processed.");
    }

    private void noticeConsole(AccountEvent accountEvent) throws InterruptedException {
        System.out.println("Notification Console Processing...");
        Thread.sleep(5000); // Simulate delay for sending SMS
        //todo integrate with SMS gateway here
        System.out.println(accountEvent.getMessage());
        System.out.println("Notification Console Processed.");
    }
}
