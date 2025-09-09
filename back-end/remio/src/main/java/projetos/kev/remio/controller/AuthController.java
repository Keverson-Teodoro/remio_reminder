package projetos.kev.remio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import projetos.kev.remio.DTO.LoginResponseDTO;
import projetos.kev.remio.DTO.UserLoginDTO;
import projetos.kev.remio.DTO.UserRegisterDTO;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.service.TokenService;
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
    TokenService tokenService;


    @GetMapping("/teste")
    public String sasd(){
        System.out.println("bateu aq");
        return "asdasd";
    }


    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserRegisterDTO userRegisterDTO){
        userService.salvar(userRegisterDTO);
        return ResponseEntity.ok(userRegisterDTO);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> Login(@RequestBody UserLoginDTO userLoginDTO){

        var usernameAndPassword = new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password());
        var auth = this.authenticationManager.authenticate(usernameAndPassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));


    }
}
