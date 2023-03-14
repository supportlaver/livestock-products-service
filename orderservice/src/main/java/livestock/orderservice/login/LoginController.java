package livestock.orderservice.login;

import livestock.orderservice.SessionConst;
import livestock.orderservice.domain.Member;
import livestock.orderservice.service.LoginService;
import livestock.orderservice.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") MemberForm form){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginCheck(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
                             HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURL){

        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember==null){
            bindingResult.reject("loginFail","아이디 비밀번호가 맞지 않습니다.");
            log.info("bindingResult.reject");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
