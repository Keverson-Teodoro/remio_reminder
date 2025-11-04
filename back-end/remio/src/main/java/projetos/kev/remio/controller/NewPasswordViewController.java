package projetos.kev.remio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NewPasswordViewController {



    @GetMapping("/newPassword-view/{code}")
    public String newPassword (@PathVariable("code") Integer code){
        return "nova_senha_page";
    }
}
