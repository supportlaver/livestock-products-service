package livestock.orderservice.domain;

import livestock.orderservice.domain.livestock.LiveStock;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
}
