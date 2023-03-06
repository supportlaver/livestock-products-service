package livestock.orderservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    //회원 이름 , 주문 상태로 검색할 수 있다.
    private String memberName;
    private OrderStatus orderStatus;


}
