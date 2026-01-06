package org.example.step_function.model;

import lombok.Builder;
import lombok.Data;
import org.example.step_function.model.enums.AccountEventEnums;

@Data
@Builder
public class AccountEvent {
    private AccountEventEnums eventType;
    private Long accountNumber;
    private String message;
    private String status;
}
