package org.example.step_function.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.example.step_function.constant.Constant;
import org.example.step_function.model.AccountContext;
import org.example.step_function.model.AccountEvent;
import org.example.step_function.model.AccountRequest;
import org.example.step_function.step.*;
import org.example.step_function.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WithdrawalService {
    private final Gson gson = new Gson();

    public void withdrawal(AccountRequest request) {
        AccountContext context = new AccountContext();
        context.setAccountRequest(request);

        new StepPipeline<AccountContext>()
                .addStep(new ValidateRequestStep())
                .addStep(new ValidateAndGetAccountStep())
                .addStep(new ProcessingWithdrawalStep())
                .addStep(new ConsoleNotificationStep())
                .addStep(new SMSNotificationStep())
                .execute(context);

        this.handleWithdrawalNotification();
        System.out.println(gson.toJson(context.getChangeLog()));
    }


    //retry here
    private void handleWithdrawalNotification() {
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
