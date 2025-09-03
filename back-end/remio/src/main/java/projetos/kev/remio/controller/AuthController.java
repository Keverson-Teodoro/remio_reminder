package projetos.kev.remio.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetos.kev.remio.DTO.UserRegisterDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {



    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserRegisterDTO userRegisterDTO){
        return null;
    }
}
