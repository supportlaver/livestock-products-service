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


}
