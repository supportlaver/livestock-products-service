package livestock.orderservice.web.form;

import livestock.orderservice.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class MemberForm {

    private Long id;

    @NotEmpty(message = "아이디는 입력은 필수값 입니다.")
    private String loginId;
    @NotEmpty(message = "이름은 필수값 입니다.")
    private String name;

    @NotEmpty(message = "도로명 주소는 필수 입니다")
    private String roadName;

    @NotEmpty(message = "상세 주소는 필수 입니다")
    private String moreInfo;

    @NotEmpty(message = "비밀번호 입력은 필수값 입니다.")
    private String password;


}
