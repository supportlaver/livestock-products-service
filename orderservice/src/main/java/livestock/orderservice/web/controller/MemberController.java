package livestock.orderservice.web.controller;

import livestock.orderservice.domain.Address;
import livestock.orderservice.domain.Member;
import livestock.orderservice.service.MemberService;
import livestock.orderservice.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createMember(Model model){
        log.info("@GetMapping(/members/new)");
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String saveMember(@Valid MemberForm memberForm, BindingResult bindingResult){
        log.info("@PostMapping(/members/new)");
        if(bindingResult.hasErrors()){
            return "members/createMemberForm";
        }
        Address address = new Address(memberForm.getRoadName(),memberForm.getMoreInfo());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String findAll(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/findMemberForm";
    }

    @GetMapping("/members/{id}/edit")
    public String updateMemberForm(@PathVariable("id") Long memberId , Model model){
        Member updateMember = memberService.findOne(memberId);
        MemberForm memberForm = new MemberForm();
        memberForm.setName(updateMember.getName());
        memberForm.setRoadName(updateMember.getAddress().getRoadName());
        memberForm.setMoreInfo(updateMember.getAddress().getMoreInfo());
        model.addAttribute("form",memberForm);
        return "members/updateMemberForm";
    }

    @PostMapping("/members/{id}/edit")
    public String updateMember(@PathVariable("id") Long memberId ,@Validated @ModelAttribute("form") MemberForm updateMemberForm
                                ,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "members/updateMemberForm";
        }
        Member memberParam = new Member();
        memberParam.setName(updateMemberForm.getName());
        Address address = new Address(updateMemberForm.getRoadName(),updateMemberForm.getMoreInfo());
        memberParam.setAddress(address);
        memberService.updateMember(memberId,memberParam);
        return "redirect:/";
    }
}
