package livestock.orderservice.domain;

import livestock.orderservice.domain.livestock.LiveStock;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Table(name="orders")
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLiveStock> orderLiveStocks = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    //연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    //연관관계 편의 메서드
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderLiveStocks(List<OrderLiveStock> orderLiveStocks) {
        this.orderLiveStocks = orderLiveStocks;
    }

    //연관관계 편의 메서드
    public void addLiveStocks(OrderLiveStock orderLiveStock){
        orderLiveStocks.add(orderLiveStock);
        orderLiveStock.setOrder(this);
    }

    public Order() {
    }

    /**
     * 주문 생성 메소드
     */
    public static Order createOrder(Member member , Delivery delivery, OrderLiveStock... orderLiveStocks){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderLiveStock o : orderLiveStocks) {
            order.addLiveStocks(o);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        LocalDateTime localDateTime = LocalDateTime.now();
        order.setOrderDate(localDateTime);
        return order;
    }

    /**
     * 비즈니스 로직 추가
     */
    public void cancel(){
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMP){
            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능 합니다.");
        }

        this.setOrderStatus(OrderStatus.CANCEL);
        for (OrderLiveStock orderLiveStock : orderLiveStocks) {
            orderLiveStock.cancel();
        }
    }
    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderLiveStock orderLiveStock : orderLiveStocks) {
            totalPrice += orderLiveStock.getTotalPrice();
        }
        return totalPrice;
    }
}
