package projetos.kev.remio.components;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import projetos.kev.remio.service.CookieService;

import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//
//        if(CookieService.getCookie(request, "Authorization") != null){
//            return true;
//        }
//        response.sendRedirect("/login-view");
//        return false;
//    }
}
