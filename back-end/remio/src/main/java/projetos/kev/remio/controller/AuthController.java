package projetos.kev.remio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import projetos.kev.remio.DTO.CodeVerifyDTO;
import projetos.kev.remio.DTO.GenerateVerifyCodeDto;
import projetos.kev.remio.DTO.NewPasswordRequestDto;
import projetos.kev.remio.DTO.UserRegisterDTO;
import projetos.kev.remio.service.AuthenticationService;
import projetos.kev.remio.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/refreshPassword")
    public ResponseEntity<String> refreshPassword(@RequestBody NewPasswordRequestDto newPasswordRequestDto){
        return authenticationService.refreshPassword(newPasswordRequestDto);
    }

    @PostMapping("/verifyCode")
    public ResponseEntity<String> verifyCode (@RequestBody CodeVerifyDTO verifyDTO){
        return null;
    }

    @PostMapping("/generateVerifyCode")
    public ResponseEntity<String> generateAndValidadePasswordRefresh(@RequestBody GenerateVerifyCodeDto email) throws Exception {
        String mail =  email.email();
        return authenticationService.validateNewPasswordRequest(email);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserRegisterDTO userRegisterDTO){
        userService.salvar(userRegisterDTO);
        return ResponseEntity.ok(userRegisterDTO);
    }

}
