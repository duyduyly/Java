package org.example.template_method.model;

import lombok.Data;

@Data
public class AccountRequest {
    private Long accountNumber;
    private Double amount;
    private String pin;
}
