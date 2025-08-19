package json.validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import json.validation.custom_annotation.LowercaseOnly;
import lombok.Data;
import lombok.Getter;

@Data
public class Person {

    private Long id;

    @NotNull
    private String name;

    @Min(18) @Max(99)
    private Integer age;

    @Email
    private String email;

    @LowercaseOnly
    private String code;
}
