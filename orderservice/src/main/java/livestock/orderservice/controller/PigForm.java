package livestock.orderservice.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PigForm {

    @NotEmpty(message = "축산물 이름은 필수 입니다.")
    private String name;
    private int price;
    private int stockQuantity;
    private String type;
}
