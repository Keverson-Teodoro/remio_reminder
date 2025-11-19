package projetos.kev.remio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import projetos.kev.remio.DTO.CodeVerifyDTO;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.model.entity.VerifyCode;
import projetos.kev.remio.repository.UserRepository;
import projetos.kev.remio.repository.VerifyCodeRepository;

@Service
public class VerifyCodeService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerifyCodeRepository verifyCodeRepository;

    public void saveCode (VerifyCode verifyCode){

        User user = userRepository.findByEmail(verifyCode.getEmail());
        if(user == null) throw new RuntimeException("Usuário não cadastrado");

        verifyCodeRepository.save(verifyCode);
    }

    public ResponseEntity<String> verifyCode (CodeVerifyDTO codeVerifyDTO){
        var code = verifyCodeRepository.findByCode(codeVerifyDTO.code());
        if (code == null) return ResponseEntity.badRequest().body("Código inválido");
        return ResponseEntity.ok("Código válido.");

    }

    public User findUserByCode (Integer code){
        return verifyCodeRepository.findUserByCode(code);
    }
}
