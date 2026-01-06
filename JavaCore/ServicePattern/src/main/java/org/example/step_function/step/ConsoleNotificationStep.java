package org.example.step_function.step;

import com.google.gson.Gson;
import org.example.step_function.constant.Constant;
import org.example.step_function.exception.StepFunctionException;
import org.example.step_function.model.AccountContext;
import org.example.step_function.model.AccountEvent;
import org.example.step_function.model.enums.AccountEventEnums;
import org.example.step_function.service.Step;
import org.example.step_function.model.Account;
import org.example.step_function.model.AccountRequest;
import org.example.step_function.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsoleNotificationStep implements Step<AccountContext> {

    private final Gson gson = new Gson();

    @Override
    public void execute(AccountContext ctx) {
        try {
            System.out.println("Console Notice Processing...");
            ctx.setStep("ConsoleNotice");

            Account account = ctx.getAccount();
            AccountRequest request = ctx.getAccountRequest();
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

            ctx.setSuccess(true);
            ctx.addChangeLog("Execute Notification Step", null);

            this.pushMessage(builder.toString(), ctx);
        } catch (Exception e) {
            throw new StepFunctionException("", "Console Notification failed", e);
        }
    }

    private void pushMessage(String message, AccountContext ctx) throws IOException, TimeoutException {
        RabbitMQUtils rabbitMQUtils = new RabbitMQUtils(Constant.QUEUE_NAME);
        AccountEvent accountEvent = AccountEvent.builder()
                .eventType(AccountEventEnums.CONSOLE)
                .accountNumber(ctx.getAccount().getAccountNumber())
                .message(message)
                .status("SUCCESS")
                .build();
        rabbitMQUtils.sendMessage(gson.toJson(accountEvent));
        System.out.println("Pushed Console Notification Queue.");
    }

    @Override
    public void rollback(AccountContext context) {
        System.err.println("Manual Rollback.");
    }
}
