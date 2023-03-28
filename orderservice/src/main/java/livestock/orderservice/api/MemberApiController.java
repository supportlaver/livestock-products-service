package livestock.orderservice.api;

import livestock.orderservice.domain.Address;
import livestock.orderservice.domain.Member;
import livestock.orderservice.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public Result members(){
        List<Member> members = memberService.findMembers();
        List<MemberDto> collect = members.stream()
                .map(m -> new MemberDto(m.getName(),m.getAddress()))
                .collect(Collectors.toList());
        return new Result(collect.size(),collect);

    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
        private Address address;
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int cnt;
        private T data;
    }


    @PutMapping("/api/members/{id}")
    public UpdateMemberResponse updateMember(@PathVariable("id") Long id , @RequestBody UpdateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());
        Member updateMember = memberService.updateMember(id, member);
        return new UpdateMemberResponse(updateMember.getName(),updateMember.getAddress());

    }

    @PostMapping("/api/members")
    public CreateMemberResponse saveMember(@RequestBody CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        member.setAddress(request.getAddress());
        Member joinMember = memberService.join(member);
        return new CreateMemberResponse(joinMember.getId(),joinMember.getAddress());
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private String name;
        private Address address;

    }

    @Data
    static class UpdateMemberRequest{
        private String name;
        private Address address;
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse{
        private Long id;
        private Address address;
    }

    @Data
    static class CreateMemberRequest{
        private String name;
        private Address address;

    }
}
