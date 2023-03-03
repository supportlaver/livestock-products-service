package livestock.orderservice.domain.livestock;

import livestock.orderservice.domain.OrderLiveStock;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LiveStock {

    @Id @GeneratedValue
    @Column(name="livestock_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_livestock_id")
    private OrderLiveStock orderLiveStock;


    /**
     * 비즈니스 로직 추가
     */
    public void addStockQuantity(int amount){
        this.stockQuantity+=amount;
    }

    public void removeStockQuantity(int amount){
        this.stockQuantity-=amount;
    }




}
