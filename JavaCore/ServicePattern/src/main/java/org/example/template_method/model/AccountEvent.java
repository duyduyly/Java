package org.example.template_method.model;

import lombok.Builder;
import lombok.Data;
import org.example.template_method.model.enums.AccountEventEnums;

@Data
@Builder
public class AccountEvent {
    private AccountEventEnums eventType;
    private Long accountNumber;
    private String message;
    private String status;
}
