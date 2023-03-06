package livestock.orderservice.service;

import livestock.orderservice.domain.Address;
import livestock.orderservice.domain.Member;
import livestock.orderservice.domain.Order;
import livestock.orderservice.domain.OrderStatus;
import livestock.orderservice.domain.livestock.LiveStock;
import livestock.orderservice.domain.livestock.Pig;
import livestock.orderservice.exception.NotEnoughStockException;
import livestock.orderservice.repository.LiveStockRepository;
import livestock.orderservice.repository.MemberRepository;
import livestock.orderservice.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;



    @Test
    @DisplayName("주문 시도 _ 성공")
    void order(){


        Member member = createMember();

        LiveStock pig = createPig();


        Long orderId = orderService.order(member, pig.getId(), 10); //memberA 가 pig를 10개 주문

        Order findOrder = orderRepository.findOne(orderId);

        //상품주문을 하면 주문 상태가 ORDER 로 되어야 한다.
        Assertions.assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDER);

        //상품주문을 하면 수량이 주문한 만큼 줄어들어야 한다.
        Assertions.assertThat(pig.getStockQuantity()).isEqualTo(90);

        //주문 가격은 수량 * 가격이다.
        Assertions.assertThat(findOrder.getTotalPrice()).isEqualTo(100000);

        //주문한 상품 수가 정확해야한다.
        Assertions.assertThat(findOrder.getOrderLiveStocks().size()).isEqualTo(1);


    }

    @Test
    @DisplayName("주어진 주량보다 더 많이 주문을 하면 예외가 발생")
    void orderFail(){
        Member member = createMember();
        LiveStock liveStock = createPig();

        Assertions.assertThatThrownBy(()->orderService.order(member,liveStock.getId(),101))
                .isInstanceOf(NotEnoughStockException.class);
    }

    @Test
    @DisplayName("주문을 취소하면 수량이 다시 원래대로 돌아와야 한다")
    void orderCancel(){

        //given
        Member member = createMember();
        LiveStock liveStock = createPig();
        Long orderId = orderService.order(member, liveStock.getId(), 50);

        //when
        orderService.orderCancel(orderId);
        Order findOrder = orderRepository.findOne(orderId);

        //then
        Assertions.assertThat(liveStock.getStockQuantity()).isEqualTo(100);
        Assertions.assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);


    }

    private LiveStock createPig() {
        LiveStock pig = new Pig();
        pig.setPrice(10000);
        pig.setName("목살");
        pig.setStockQuantity(100);
        em.persist(pig);
        return pig;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("memberA");
        Address address = new Address("road1","111-111");
        member.setAddress(address);
        em.persist(member);
        return member;
    }


}