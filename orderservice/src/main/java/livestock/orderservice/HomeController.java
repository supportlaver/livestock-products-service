package livestock.orderservice;

import livestock.orderservice.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {

    //    @RequestMapping("/")
    public String home() {
        log.info("controller");
        return "home";
    }

    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        /**
         * 세션 없으면 로그인 폼으로 이동
         */
        if(session==null){
            return "home";
        }
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        /**
         * 세션 있으면 로그인 성공 화면으로 가고 거기서 상품관리 누르면 내가 만들었던 폼 나오게 됨
         */

        model.addAttribute("member",loginMember);
        return "loginHome";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "myHome";
    }

}