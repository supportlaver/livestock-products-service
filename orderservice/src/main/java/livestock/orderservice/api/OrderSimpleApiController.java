package livestock.orderservice.api;

import livestock.orderservice.domain.Address;
import livestock.orderservice.domain.Order;
import livestock.orderservice.domain.OrderSearch;
import livestock.orderservice.domain.OrderStatus;
import livestock.orderservice.repository.OrderRepository;
import livestock.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    /**
     * XToOne 관계 성능 최적화 (fetch join 으로)
     */

    @GetMapping("/api/orders/notUse") // N+1 문제가 발생하는 API 사용하면 안 된다.
    public Result notUseOrders(){
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        List<OrderSimpleDto> result = all.stream()
                .map(o -> new OrderSimpleDto(o))
                .collect(Collectors.toList());
        return new Result(result);
    }

    @GetMapping("/api/orders")
    public Result orders(){
        List<Order> all = orderRepository.findWithFetchJoin();
        List<OrderSimpleDto> result = all.stream()
                .map(o -> new OrderSimpleDto(o))
                .collect(Collectors.toList());
        return new Result(result);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    static class OrderSimpleDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public OrderSimpleDto(Order o){
            this.orderId=o.getId();
            this.name=o.getMember().getName();
            this.orderDate=o.getOrderDate();
            this.orderStatus=o.getOrderStatus();
            this.address=o.getDelivery().getAddress();
        }



    }



}
