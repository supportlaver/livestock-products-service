package livestock.orderservice.service;

import livestock.orderservice.domain.Member;
import livestock.orderservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 등록
    @Transactional
    public Member join(Member member){
        return memberRepository.addMember(member);
    }
    //회원 조회
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }
    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 수정

    @Transactional
    public Member updateMember(Long id, Member updateMember){
        Member findMember = memberRepository.findOne(id);
        findMember.setName(updateMember.getName());
        findMember.setAddress(updateMember.getAddress());
        return findMember;
    }

    //회원 삭제
    public void deleteMember(Member member){
        memberRepository.deleteMember(member);
    }


}
