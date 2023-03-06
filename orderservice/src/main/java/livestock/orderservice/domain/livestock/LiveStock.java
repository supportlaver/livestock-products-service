package livestock.orderservice.domain.livestock;

import livestock.orderservice.domain.OrderLiveStock;
import livestock.orderservice.exception.NotEnoughStockException;
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
        int restStock = this.stockQuantity-amount;

        if (restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity=restStock;

        //주문이 들어와서 수량을 그만큼 감소시키려고 하는데 감소시켰을때 0보다 작다는건 기존 수량보다 더 많이 주문했다는것이니까 여기서 예외를 터뜨린다.


    }




}
