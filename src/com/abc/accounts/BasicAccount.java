package com.abc.accounts;


public class BasicAccount {
    private static int totalNumberOfAccounts = 0;

    private int accountNumber;

    public BasicAccount() {
        this.accountNumber = ++totalNumberOfAccounts;
    }

    public String toString() {
        return "BasicAccount #" + accountNumber;
    }
}