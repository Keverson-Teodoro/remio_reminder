package projetos.kev.remio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projetos.kev.remio.DTO.GenerateVerifyCodeDto;
import projetos.kev.remio.DTO.NewPasswordRequestDto;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.model.entity.VerifyCode;
import projetos.kev.remio.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;

    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    PasswordEncoder encoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private JavaMailSender javaMailSender;

    @Autowired
    VerifyCodeService verifyCodeService;

    public AuthenticationService (JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public ResponseEntity<String> refreshPassword(NewPasswordRequestDto newPasswordRequestDto){

        userService.saveNewUserPassword(newPasswordRequestDto);
        return ResponseEntity.ok().build();
    }

    public Integer validateNewPasswordRequest(GenerateVerifyCodeDto generateVerifyCodeDto){
        User user = userRepository.findByEmail(generateVerifyCodeDto.email());
        if(user == null) throw new RuntimeException("Usuario não possui conta");

        Integer code = generateRecoverPasswordCode();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Redefinição de senha");
        message.setText("Código: " + code);
        message.setTo(user.getEmail());
        message.setFrom(mailFrom);
        javaMailSender.send(message);

        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setCode(code);
        verifyCode.setEmail(user.getEmail());
        verifyCode.setUser(user);
        verifyCode.setSendCodeDate(LocalDateTime.now());

        verifyCodeService.saveCode(verifyCode);
        return code;

    }

    public Integer generateRecoverPasswordCode(){
        Random codeGenetaror = new Random();
        return codeGenetaror.nextInt(100000);
    }

    public Instant generateCodeExpirateDate(VerifyCode verifyCode){

        return verifyCode.getSendCodeDate().plusMinutes(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
