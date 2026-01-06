package org.example.step_function.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AccountEventEnums {
    SMS,
    CONSOLE;

    private String value;
}
