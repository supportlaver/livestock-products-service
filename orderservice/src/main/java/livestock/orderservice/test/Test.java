package livestock.orderservice.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Test {

    private String name;
    private Long id;
    private String number;

    public Test() {
    }
}
