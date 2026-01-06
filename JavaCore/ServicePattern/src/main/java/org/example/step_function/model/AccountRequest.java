package org.example.step_function.model;

import lombok.Data;

@Data
public class AccountRequest {
    private Long accountNumber;
    private Double amount;
    private String pin;
}
