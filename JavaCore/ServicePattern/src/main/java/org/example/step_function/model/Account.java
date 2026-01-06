package org.example.step_function.model;

import lombok.Data;

import java.util.List;

@Data
public class Account {
    private Long accountNumber;
    private String name;
    private Double balance;
    private String pin;
    private Boolean active;
    private String currency;
    private Double withdrawalLimit = 5000D; // Default withdrawal limit

    public Account(Long accountNumber, String name, Double balance, String currency) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(Long accountNumber, String name, Double balance, String pin, Boolean active, String currency) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.pin = pin;
        this.active = active;
        this.currency = currency;
    }

    public Account() {
    }

    public static List<Account> mockData(){
        return List.of(
                new Account(123L, "Alan", 100D, "123456", true, "USD"),
                new Account(999L, "Peter", 1000D, "232132", false, "USD"),
                new Account(3232L, "Nam", 20D, "1323456", true, "USD"),
                new Account(6565L, "Lan", 35D, "324156", true, "USD")
        );
    }
}
