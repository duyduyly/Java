package org.example.step_function.step;

import com.google.gson.Gson;
import org.example.step_function.constant.Constant;
import org.example.step_function.model.AccountEvent;
import org.example.step_function.model.enums.AccountEventEnums;
import org.example.step_function.service.Step;
import org.example.step_function.exception.StepFunctionException;
import org.example.step_function.model.AccountContext;
import org.example.step_function.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SMSNotificationStep implements Step<AccountContext> {

    private final Gson gson = new Gson();

    @Override
    public void execute(AccountContext ctx) {
        try {
            System.out.println("SMS Notice Processing...");
            ctx.setStep("SMSNotice");
            // Simulate sending SMS

            //handle message SMS
            this.pushMessage("SMS Notice", ctx);
            ctx.setSuccess(true);
            ctx.addChangeLog("Execute Notification Step", null);

        } catch (Exception e) {
            throw new StepFunctionException("", "SMS Notification failed", e);
        }
    }

    private void pushMessage(String message, AccountContext ctx) throws IOException, TimeoutException {
        RabbitMQUtils rabbitMQUtils = new RabbitMQUtils(Constant.QUEUE_NAME);
        AccountEvent accountEvent = AccountEvent.builder()
                .eventType(AccountEventEnums.SMS)
                .accountNumber(ctx.getAccount().getAccountNumber())
                .message(message)
                .status("SUCCESS")
                .build();

        rabbitMQUtils.sendMessage(gson.toJson(accountEvent));
        System.out.println("Pushed SMS Notification Queue.");
    }

    @Override
    public void rollback(AccountContext context) {
        System.err.println("Manual Rollback.");
    }
}
