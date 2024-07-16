package com.jpa.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Data
@Builder
public class CommonResponse {
    private int status;
    private Object data;
    private String message;

    public static CommonResponse getCommonResponseSuccess(Object data){
        return CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .data(data)
                .message("Get All Books Success")
                .build();
    }
}
