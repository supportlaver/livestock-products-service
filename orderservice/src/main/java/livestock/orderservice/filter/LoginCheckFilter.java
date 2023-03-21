package livestock.orderservice.filter;

import livestock.orderservice.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/members/new","/css/*" , "/login" , "/logout" };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        try{
            if(isLoginCheckPath(requestURI))
            {
                HttpSession session = httpRequest.getSession(false);
                if (session==null || session.getAttribute(SessionConst.LOGIN_MEMBER)==null)
                {
                    httpResponse.sendRedirect("/login?redirectURL="+requestURI);

                }
            }
            chain.doFilter(request,response);

        }catch (Exception e){
            throw e;
        } finally {
            log.info("종료");

        }



    }

    public boolean isLoginCheckPath(String requestURI)
    {
        return !PatternMatchUtils.simpleMatch(whitelist,requestURI);

    }

}
