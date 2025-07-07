package concurrency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Package {
    private String name;
    private List<Snack> snack;
    private String status = PackageEnums.PROCESSING.getStatusName();
    private int totalSnack;

    public String toStringList(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.snack.size(); i++) {
            sb.append("| ").append(i + 1).append(" | ").append(this.snack.get(i).toString());
        }
        return sb.toString();
    }
}
