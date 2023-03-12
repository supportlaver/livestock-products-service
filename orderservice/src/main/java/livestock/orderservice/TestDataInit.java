package livestock.orderservice;


import livestock.orderservice.domain.Address;
import livestock.orderservice.domain.Member;
import livestock.orderservice.domain.livestock.LiveStock;
import livestock.orderservice.service.LiveStockService;
import livestock.orderservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;
    private final LiveStockService liveStockService;

    @PostConstruct
    public void init(){

        memberService.join(new Member(new Address("호구포로 13-1","101동 3202호"),"김지원"));
        memberService.join(new Member(new Address("호구포로 13-1","103동 3001호"),"김규완"));

        liveStockService.addLiveStock(new LiveStock("돼지고기",10000,100));
        liveStockService.addLiveStock(new LiveStock("소고기",18000,50));
        liveStockService.addLiveStock(new LiveStock("닭",7000,200));
    }


}
