package org.example.template_method;

import org.example.template_method.model.AccountRequest;
import org.example.template_method.service.WithdrawalService;
import org.example.template_method.service.impl.WithdrawalServiceImpl;

public class App {
    private static final WithdrawalService withdrawalService = new WithdrawalServiceImpl();

    public static void main(String[] args) {
        withdrawal_Success();
//        withdrawal_BalanceNotEnough_Fail();
//        withdrawal_WrongInformation_Fail();
//        withdrawal_WrongPin_Fail();
//        withdrawal_AccountIsNotActive_Fail();
    }

    public static void withdrawal_Success() {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(123L);
        request.setAmount(50D);
        request.setPin("123456");
        withdrawalService.process(request);
    }

    public static void withdrawal_WrongInformation_Fail() {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(111111L);
        request.setAmount(10D);
        request.setPin("123456");
        withdrawalService.process(request);
    }

    public static void withdrawal_BalanceNotEnough_Fail() {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(123L);
        request.setAmount(120D);
        request.setPin("123456");
        withdrawalService.process(request);
    }

    public static void withdrawal_WrongPin_Fail() {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(123L);
        request.setAmount(120D);
        request.setPin("123422");
        withdrawalService.process(request);
    }


    public static void withdrawal_AccountIsNotActive_Fail() {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(999L);
        request.setAmount(20D);
        request.setPin("232132");
        withdrawalService.process(request);
    }
}
