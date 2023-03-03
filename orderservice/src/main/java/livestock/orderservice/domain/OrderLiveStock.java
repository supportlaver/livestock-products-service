package livestock.orderservice.domain;

import livestock.orderservice.domain.livestock.LiveStock;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="order_livestock")
public class OrderLiveStock {

    @Id @GeneratedValue
    @Column(name="order_livestock_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="livestock_id")
    private LiveStock liveStock;


    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;

    private int count;

    /**
     * OrderLiveStock 생성 메소드
     */
    public static OrderLiveStock createOrderLiveStock(LiveStock liveStock , int orderPrice, int count){
        OrderLiveStock orderLiveStock = new OrderLiveStock();
        orderLiveStock.setLiveStock(liveStock);
        orderLiveStock.setOrderPrice(orderPrice);
        orderLiveStock.setCount(count);
        return orderLiveStock;
    }
    /**
     * 비즈니스 로직 추가
     */
    public void cancel() {
        getLiveStock().addStockQuantity(count);
    }
    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }

}
