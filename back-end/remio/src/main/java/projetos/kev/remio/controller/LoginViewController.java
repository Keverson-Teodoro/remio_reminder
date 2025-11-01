package projetos.kev.remio.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import projetos.kev.remio.DTO.UserLoginDTO;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.service.CookieService;
import projetos.kev.remio.service.TokenService;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginViewController {

    @Autowired
    public AuthController authController;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    private CookieService cookieService;

    @GetMapping("/login-view")
    public String paginaLogin (){return "login_page";}

//    @PostMapping("/login")
//    public String login (UserLoginDTO userLoginDTO, HttpServletResponse response) throws UnsupportedEncodingException {
//
//        var usernameAndPassword = new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password());
//        var auth = this.authenticationManager.authenticate(usernameAndPassword);
//        var token = tokenService.generateToken((User)auth.getPrincipal());
//
//        if (token != null){
//            cookieService.setCookie(response, "Authorization", "Bearer_" + token);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            return "redirect:/";
//        }
//        return "redirect:/login-view";
//    }
}
