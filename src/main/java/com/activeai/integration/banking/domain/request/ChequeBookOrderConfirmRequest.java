package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.model.Account;
import com.activeai.integration.banking.model.User;

public class ChequeBookOrderConfirmRequest extends User {

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
