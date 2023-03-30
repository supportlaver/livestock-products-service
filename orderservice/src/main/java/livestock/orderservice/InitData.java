package livestock.orderservice;

import livestock.orderservice.domain.*;
import livestock.orderservice.domain.livestock.LiveStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.initDb1();
        initService.initDb2();
    }



    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService{

        private final EntityManager em;

        public void initDb1(){

            //Member 2명 생성 , LiveStock 2개 생성 , Order 2개 생성

            Address address = new Address("API1", "123-123");

            Member member = new Member();
            member.setName("API1");
            member.setAddress(address);
            em.persist(member);

            LiveStock liveStock1 = new LiveStock();
            liveStock1.setName("채끝");
            liveStock1.setStockQuantity(100);
            liveStock1.setPrice(25000);
            em.persist(liveStock1);

            LiveStock liveStock2 = new LiveStock();
            liveStock2.setName("안창살");
            liveStock2.setStockQuantity(200);
            liveStock2.setPrice(20000);
            em.persist(liveStock2);

            Delivery delivery = new Delivery();
            delivery.setAddress(address);
            em.persist(delivery);

            OrderLiveStock orderLiveStock1 = OrderLiveStock.createOrderLiveStock(liveStock1, 25000, 2);

            OrderLiveStock orderLiveStock2 = OrderLiveStock.createOrderLiveStock(liveStock2, 20000, 3);

            Order order = Order.createOrder(member, delivery, orderLiveStock1, orderLiveStock2);
            em.persist(order);
        }

        public void initDb2(){

            //Member 2명 생성 , LiveStock 2개 생성 , Order 2개 생성

            Address address = new Address("API2", "456-456");

            Member member = new Member();
            member.setName("AP2");
            member.setAddress(address);
            em.persist(member);

            LiveStock liveStock1 = new LiveStock();
            liveStock1.setName("등갈비");
            liveStock1.setStockQuantity(30);
            liveStock1.setPrice(15000);
            em.persist(liveStock1);

            LiveStock liveStock2 = new LiveStock();
            liveStock2.setName("양갈비");
            liveStock2.setStockQuantity(50);
            liveStock2.setPrice(10000);
            em.persist(liveStock2);

            Delivery delivery = new Delivery();
            delivery.setAddress(address);
            em.persist(delivery);

            OrderLiveStock orderLiveStock1 = OrderLiveStock.createOrderLiveStock(liveStock1, 15000, 1);

            OrderLiveStock orderLiveStock2 = OrderLiveStock.createOrderLiveStock(liveStock2, 10000, 4);

            Order order = Order.createOrder(member, delivery, orderLiveStock1, orderLiveStock2);
            em.persist(order);
        }

    }
}
