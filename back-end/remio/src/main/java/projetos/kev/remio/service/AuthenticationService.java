package projetos.kev.remio.service;


import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import projetos.kev.remio.DTO.GenerateVerifyCodeDto;
import projetos.kev.remio.DTO.NewPasswordRequestDto;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.model.entity.VerifyCode;
import projetos.kev.remio.repository.UserRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    private PasswordEncoder encoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freMakerConfig;

    @Autowired
    private VerifyCodeService verifyCodeService;

    public AuthenticationService (JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public ResponseEntity<String> refreshPassword(NewPasswordRequestDto newPasswordRequestDto){

        userService.saveNewUserPassword(newPasswordRequestDto, newPasswordRequestDto.getCode());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> validateNewPasswordRequest(GenerateVerifyCodeDto generateVerifyCodeDto) throws Exception{
        User user = userRepository.findByEmail(generateVerifyCodeDto.email());
        if(user == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Usuário não possui email cadastrado.");
        };

        Integer code = generateRecoverPasswordCode();

        Map<String, Object> model = new HashMap<>();
        model.put("codigo", code);

        try {
            Template template = freMakerConfig.getTemplate("recuperar_senha_template.ftl");
            String emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setText(emailContent, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Redefinição de senha");
            helper.setFrom(mailFrom);

            javaMailSender.send(message);

            VerifyCode verifyCode = new VerifyCode();
            verifyCode.setCode(code);
            verifyCode.setEmail(user.getEmail());
            verifyCode.setUser(user);
            verifyCode.setSendCodeDate(LocalDateTime.now());

            verifyCodeService.saveCode(verifyCode);
            return ResponseEntity.ok("Email enviado com sucesso!");

        }catch (MailException exception) {
            return ResponseEntity.internalServerError().body("Não foi possivel enviar o email");
        }
    }

    public Integer generateRecoverPasswordCode(){
        SecureRandom codeGenetaror = new SecureRandom();
        return codeGenetaror.nextInt(999999);
    }

}
