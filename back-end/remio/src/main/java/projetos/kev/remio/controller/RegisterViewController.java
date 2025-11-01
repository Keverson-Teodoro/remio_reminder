package projetos.kev.remio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projetos.kev.remio.DTO.UserRegisterDTO;
import projetos.kev.remio.service.UserService;

@Controller
public class RegisterViewController {

    @Autowired
    private UserService userService;

    @GetMapping("/register-view")
    public String registerPage(){return "register_page";}

    @RequestMapping(value = "/register-view", method = RequestMethod.POST)
    public String registryUser (UserRegisterDTO userRegisterDTO){

        userService.salvar(userRegisterDTO);
        return "redirect:/login-view";
    }
}
