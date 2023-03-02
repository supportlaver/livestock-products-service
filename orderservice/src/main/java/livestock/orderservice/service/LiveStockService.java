package livestock.orderservice.service;

import livestock.orderservice.domain.livestock.LiveStock;
import livestock.orderservice.repository.LiveStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LiveStockService {

    private final LiveStockRepository liveStockRepository;

    //축산물 등록
    @Transactional
    public LiveStock addLiveStock(LiveStock liveStock){
        liveStockRepository.addLiveStock(liveStock);
        return liveStock;
    }

    //축산물 수정
    @Transactional
    public LiveStock updatePriceLiveStock(LiveStock liveStock , int price){
        LiveStock updateLiveStock = liveStockRepository.findById(liveStock.getId());
        updateLiveStock.setPrice(price);
        return updateLiveStock;
    }

    @Transactional
    public LiveStock updateLiveStock(Long id,LiveStock liveStock){
        LiveStock findLiveStock = liveStockRepository.findById(id);
        findLiveStock.setStockQuantity(liveStock.getStockQuantity());
        findLiveStock.setName(liveStock.getName());
        findLiveStock.setOrderLiveStock(liveStock.getOrderLiveStock());
        findLiveStock.setPrice(liveStock.getPrice());
        return findLiveStock;
    }

    //축산물 조회
    public LiveStock findOne(Long id){
        return liveStockRepository.findById(id);
    }

    public List<LiveStock> findAll(){
        return liveStockRepository.findAll();
    }


}
