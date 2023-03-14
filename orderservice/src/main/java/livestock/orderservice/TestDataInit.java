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
        memberService.join(new Member((new Address("호구포로 13-1","101동 3202호")),"테스터","test","test!"));
        memberService.join(new Member((new Address("호구포로 13-1","101동 3203호")),"테스터2","test2","test2!"));

        liveStockService.addLiveStock(new LiveStock("돼지고기",10000,100));
        liveStockService.addLiveStock(new LiveStock("소고기",18000,50));
        liveStockService.addLiveStock(new LiveStock("닭",7000,200));
    }


}
