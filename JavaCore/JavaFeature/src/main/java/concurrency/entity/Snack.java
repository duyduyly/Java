package concurrency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Snack {
    private String snackName;
    private int quantity;
    private double price;
    private boolean status;

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s |\n", snackName, quantity, price, status, quantity);
    }
}
