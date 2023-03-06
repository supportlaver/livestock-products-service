package livestock.orderservice.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이름은 필수값 입니다.")
    private String name;
    private String roadName; //도로명 주소
    private String moreInfo; //상세 주소
}
