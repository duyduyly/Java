package org.example.step_function.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class StepFunctionException extends RuntimeException {
    private String message;
    private String code;
    private Exception exception;

    public StepFunctionException(String code, String message, Exception exception) {
        super(message, exception);
        this.code = code;
        this.message = message;
        this.exception = exception;
    }

    public String toMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("StepFunctionException").append(" - ");
        if (Objects.nonNull(this.code) && (!this.code.isEmpty())) builder.append("Code: ").append(code).append(" - ");
        if (Objects.nonNull(this.message)) builder.append("Message: ").append(message).append(" - ");
        if (Objects.nonNull(this.exception)) builder.append("Exception: ").append(exception);
        return builder.toString();
    }


}
