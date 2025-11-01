package projetos.kev.remio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import projetos.kev.remio.DTO.*;
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

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserRegisterDTO userRegisterDTO){
        userService.salvar(userRegisterDTO);
        return ResponseEntity.ok(userRegisterDTO);
    }

    @DeleteMapping("/id")
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }
}
