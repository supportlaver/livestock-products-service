package livestock.orderservice.controller;


import livestock.orderservice.domain.Member;
import livestock.orderservice.domain.Order;
import livestock.orderservice.domain.OrderSearch;
import livestock.orderservice.domain.livestock.LiveStock;
import livestock.orderservice.service.LiveStockService;
import livestock.orderservice.service.MemberService;
import livestock.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final LiveStockService liveStockService;

    @GetMapping("/order")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<LiveStock> liveStocks = liveStockService.findAll();
        model.addAttribute("members",members);
        model.addAttribute("liveStocks",liveStocks);
        return "order/orderForm";
    }
    @PostMapping("/order")
    public String saveForm(@RequestParam("memberId") Long memberId ,
                           @RequestParam("count") int count,
                           @RequestParam("liveStockId") Long liveStockId){
        Member findMember = memberService.findOne(memberId);
        orderService.order(findMember,liveStockId,count);
        return "redirect:/";
    }

    @GetMapping("/orders")
    public String findOrders(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);
        return "order/orderList";

    }


    @PostMapping("/orders/{orderId}/cancel")
    public String cancel(@PathVariable("orderId") Long orderId){
        orderService.orderCancel(orderId);
        return "redirect:/";


    }



}
