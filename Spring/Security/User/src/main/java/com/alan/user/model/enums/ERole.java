package com.alan.user.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ERole {
    ADMIN(1, "ADMIN"),
    USER(2, "USER"),
    MODERATOR(2, "MODERATOR");

    private int id;
    private String value;
}
