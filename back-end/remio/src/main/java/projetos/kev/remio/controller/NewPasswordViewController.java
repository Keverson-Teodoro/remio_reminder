package projetos.kev.remio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projetos.kev.remio.DTO.NewPasswordRequestDto;
import projetos.kev.remio.service.AuthenticationService;

@Controller
@RequiredArgsConstructor
public class NewPasswordViewController {

    private final AuthenticationService authenticationService;

    @GetMapping("/newPassword-view")
    public String newPassword (Integer code){
        return "send_code_and_new_password_page";
    }

//    @RequestMapping(value = "/newPassword-view/{code}", method = RequestMethod.POST)
//    public void saveNewPassword (NewPasswordRequestDto newPasswordRequestDto){
//        authenticationService.refreshPassword(newPasswordRequestDto, );
//    }
}
