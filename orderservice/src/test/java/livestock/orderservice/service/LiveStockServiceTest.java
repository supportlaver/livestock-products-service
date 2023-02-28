package livestock.orderservice.service;

import livestock.orderservice.domain.livestock.Cow;
import livestock.orderservice.domain.livestock.LiveStock;
import livestock.orderservice.domain.livestock.Pig;
import livestock.orderservice.repository.LiveStockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LiveStockServiceTest {

    @Autowired
    LiveStockService liveStockService;
    @Autowired
    LiveStockRepository liveStockRepository;

    @Test
    void addLiveStock(){
        //given
        LiveStock liveStock1 = makePig();
        //when
        LiveStock findLiveStock = liveStockService.findOne(liveStock1.getId());
        //then
        Assertions.assertThat(findLiveStock).isEqualTo(liveStock1);
        Assertions.assertThat(findLiveStock.getName()).isEqualTo(liveStock1.getName());
    }

    @Test
    void checkFindAll(){
        makePig();

        makeCow();

        List<LiveStock> result = liveStockService.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }



    @Test
    void updateLiveStock(){
        LiveStock makeCow = makeCow();
        LiveStock updateLiveStock = liveStockService.updatePriceLiveStock(makeCow, 20000);

        Assertions.assertThat(updateLiveStock.getPrice()).isEqualTo(20000);


    }

    private LiveStock makeCow() {
        LiveStock liveStock2 = new Cow();
        liveStock2.setPrice(16000);
        liveStock2.setName("등심");
        liveStock2.setStockQuantity(5);
        liveStockService.addLiveStock(liveStock2);
        return liveStock2;
    }

    private LiveStock makePig() {
        LiveStock liveStock1 = new Pig(); //돼지고기
        liveStock1.setPrice(8000);
        liveStock1.setName("목살");
        liveStock1.setStockQuantity(10);
        liveStockService.addLiveStock(liveStock1);
        return liveStock1;
    }

}