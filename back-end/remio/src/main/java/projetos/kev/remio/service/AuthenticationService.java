package projetos.kev.remio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.repository.UserRepository;

import java.util.Random;

@Service
public class AuthenticationService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Value("${api.security.token,secret}")
    private String secret;




    public void refreshPassword (String email){
        User user = userRepository.findByEmail(email);
        if(user == null) throw new RuntimeException("Usuario não possui conta");

        Integer code = generateRecoverPasswordCode();
    }


    public Integer generateRecoverPasswordCode(){
        Random codeGenetaror = new Random();
        return codeGenetaror.nextInt(6);
    }


}
