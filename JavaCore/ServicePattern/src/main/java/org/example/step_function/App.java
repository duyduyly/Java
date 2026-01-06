package org.example.step_function;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.step_function.service.WithdrawalService;
import org.example.step_function.model.AccountRequest;

public class App {
    private static final WithdrawalService withdrawalService = new WithdrawalService();

    public static void main(String[] args) throws JsonProcessingException {
        withdrawal_Success();
//        withdrawal_BalanceNotEnough_Fail();
//        withdrawal_WrongInformation_Fail();
//        withdrawal_WrongPin_Fail();
//        withdrawal_AccountIsNotActive_Fail();
    }


    public static void withdrawal_Success() throws JsonProcessingException {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(123L);
        request.setAmount(50D);
        request.setPin("123456");
        withdrawalService.withdrawal(request);
    }

    public static void withdrawal_WrongInformation_Fail() throws JsonProcessingException {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(111111L);
        request.setAmount(10D);
        request.setPin("123456");
        withdrawalService.withdrawal(request);
    }

    public static void withdrawal_BalanceNotEnough_Fail() throws JsonProcessingException {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(123L);
        request.setAmount(120D);
        request.setPin("123456");
        withdrawalService.withdrawal(request);
    }

    public static void withdrawal_WrongPin_Fail() throws JsonProcessingException {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(123L);
        request.setAmount(120D);
        request.setPin("123422");
        withdrawalService.withdrawal(request);
    }


    public static void withdrawal_AccountIsNotActive_Fail() throws JsonProcessingException {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(999L);
        request.setAmount(20D);
        request.setPin("232132");
        withdrawalService.withdrawal(request);
    }
}
