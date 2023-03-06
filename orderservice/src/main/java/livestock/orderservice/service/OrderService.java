package livestock.orderservice.service;

import livestock.orderservice.domain.*;
import livestock.orderservice.domain.livestock.LiveStock;
import livestock.orderservice.repository.LiveStockRepository;
import livestock.orderservice.repository.MemberRepository;
import livestock.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final LiveStockRepository liveStockRepository;
    private final OrderRepository orderRepository;

    //주문 Member , itemId , count
    public Long order(Member member, Long liveStockId, int count){

        //엔티티 조회
        Member findMember = memberRepository.findOne(member.getId());
        LiveStock findLiveStock = liveStockRepository.findById(liveStockId);

        //배달정보 생성
        Delivery delivery = new Delivery(member.getAddress());

        //주문상품 생성
        OrderLiveStock orderLiveStock = OrderLiveStock.createOrderLiveStock(findLiveStock,findLiveStock.getPrice(),count);

        //주문 생성
        Order order = Order.createOrder(findMember,delivery,orderLiveStock);


        //주문저장
        orderRepository.save(order);

        return order.getId();

    }

    //주문 취소
    public void orderCancel(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

}
