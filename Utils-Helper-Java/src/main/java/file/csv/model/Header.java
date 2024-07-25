package file.csv.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@ToString
@Builder
public class Header {
    private Date fileDate;
}
