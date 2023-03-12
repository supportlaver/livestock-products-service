package livestock.orderservice.web.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PigForm {

    private Long id;

    @NotEmpty(message = "축산물 이름은 필수 입니다.")
    private String name;

    private Integer price;
    @Range(min=1,max=100,message = "수량은 1~100개 이내로 설정하셔야 합니다")
    private int stockQuantity;
    private String type;
}
