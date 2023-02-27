package livestock.orderservice.repository;

import livestock.orderservice.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    //등록 Member 반환
    public Member addMember(Member member){
        em.persist(member);
        return member;
    }
    //회원 한 명 조회
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    //회원 전체 조회
    public List<Member> findAll(){
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return members;
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }

    //회원 삭제
    public void deleteMember(Member member){
        em.remove(member);
    }





}
