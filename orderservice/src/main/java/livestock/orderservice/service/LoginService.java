package livestock.orderservice.service;

import livestock.orderservice.domain.Member;
import livestock.orderservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId , String password){
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        Member member = findMember.get();
        if (member.getPassword().equals(password)){
            return member;
        }
        return null;
    }


}
