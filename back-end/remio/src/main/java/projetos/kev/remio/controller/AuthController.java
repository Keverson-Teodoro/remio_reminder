package projetos.kev.remio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import projetos.kev.remio.DTO.*;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.service.AuthenticationService;
import projetos.kev.remio.service.TokenService;
import projetos.kev.remio.service.UserService;

import java.time.Instant;

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

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/refreshPassword")
    public ResponseEntity<String> refreshPassword(@RequestBody NewPasswordRequestDto newPasswordRequestDto){
        return authenticationService.refreshPassword(newPasswordRequestDto);
    }

    @PostMapping("/generateVerifyCode")
    public ResponseEntity<String> generateAndValidadePasswordRefresh(@RequestBody GenerateVerifyCodeDto email){
        String mail =  email.email();
        authenticationService.validateNewPasswordRequest(email);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserRegisterDTO userRegisterDTO){
        userService.salvar(userRegisterDTO);
        return ResponseEntity.ok(userRegisterDTO);
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO){
//
//        var usernameAndPassword = new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password());
//        var auth = this.authenticationManager.authenticate(usernameAndPassword);
//        var token = tokenService.generateToken((User)auth.getPrincipal());
//
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//    }

    @DeleteMapping("/id")
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }

    @GetMapping("/teste")
    public Instant teste(){
        return authenticationService.generateCodeExpirateDatee();
    }
}
