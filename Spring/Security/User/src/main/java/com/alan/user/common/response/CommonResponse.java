package com.alan.user.common.response;

import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse {
    private int status;
    private String message;
    private Object data;
}
