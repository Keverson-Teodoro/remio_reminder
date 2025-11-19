package projetos.kev.remio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import projetos.kev.remio.service.AuthenticationService;

@Controller
@RequiredArgsConstructor
public class NewPasswordViewController {

    private final AuthenticationService authenticationService;

    @GetMapping("/newPassword-view")
    public String newPassword (Integer code){
        return "send_code_and_new_password_page";
    }
}
