package livestock.orderservice.service;

import livestock.orderservice.domain.Member;
import livestock.orderservice.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void add(){ //회원을 추가를 하고 리포지토리에서 찾았을떄 실제로 찾아지는지 그걸 확인하면 되는거지
        //given
        Member member = new Member();
        member.setName("userA");
        //when
        memberService.join(member);
        //then
        Member findMember = memberService.findOne(member.getId());
        Assertions.assertThat(member).isEqualTo(findMember);
    }
    @Test
    void update(){
        Member member = new Member();
        member.setName("userA");
        memberService.join(member);

        //when
        memberService.updateMember(member,"userB");
        //then
        Assertions.assertThat(member.getName()).isEqualTo("userB");
    }

    @Test
    void delete(){
        Member member = new Member();
        member.setName("userA");
        memberService.join(member);

        //when
        memberService.deleteMember(member);

        //then
        Member findMember = memberService.findOne(member.getId());

        Assertions.assertThat(findMember).isNull();
    }

    @Test
    void findMembers(){
        Member memberA = new Member();
        memberA.setName("userA");

        Member memberB = new Member();
        memberB.setName("userB");

        memberService.join(memberA);
        memberService.join(memberB);

        List<Member> result = memberService.findMembers();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}