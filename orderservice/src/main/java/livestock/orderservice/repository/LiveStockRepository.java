package livestock.orderservice.repository;

import livestock.orderservice.domain.livestock.LiveStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LiveStockRepository {

    @PersistenceContext
    private EntityManager em;

    //축산물 등록
    public LiveStock addLiveStock(LiveStock liveStock){
        em.persist(liveStock);
        return liveStock;
    }

    //축산물 단건 조회
    public LiveStock findById(Long id){
        LiveStock findLiveStock = em.find(LiveStock.class, id);
        return findLiveStock;
    }

    /**
     * 조회는 모두 조회하는 경우와 가격 , 축산물 종류(등심,안심,삼겹살..등등)으로도 검색할 수 있어야 한다.
     */
    //축산물 전체 조회 여기서는 동적 쿼리 작성이 필요하다.
    public List<LiveStock> findAll(){
        List<LiveStock> result = em.createQuery("select l from LiveStock l", LiveStock.class)
                .getResultList();
        return result;
    }



}
