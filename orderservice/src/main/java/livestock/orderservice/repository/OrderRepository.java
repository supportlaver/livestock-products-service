package livestock.orderservice.repository;

import livestock.orderservice.domain.Order;
import livestock.orderservice.domain.livestock.LiveStock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    //상품 등록
    public void order(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAll(){
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    //일단 다 하고 검색 조건에 대해서 생각

    //상품 수정?

    //상품 조회




}
