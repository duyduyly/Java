package org.example.template_method.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class AccountContext extends Context {

    @Setter
    @Getter
    private boolean validated;

    @Setter
    @Getter
    private boolean deductedAmount;

    @Setter
    @Getter
    private boolean success;

    @Getter
    private AccountRequest accountRequest;

    @Getter
    private Account account;

    //just set once
    public void setAccountRequest(AccountRequest request) {
        if (Objects.isNull(this.accountRequest)) {
            this.accountRequest = request;
        }
    }

    //just set once
    public void setAccount(Account account) {
        if (Objects.isNull(this.account)) {
            this.account = account;
        }
    }

    //Custom to log Context Change
    @Setter
    @Getter
    private String step;


    public void addChangeLog(String message, Object data) {
        super.addChangeLog(this.step, message, data);
    }
}
