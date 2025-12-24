package concurrency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum PackageEnums {
    PROCESSING("processing"),
    DONE("done");


    private final String statusName;

}
