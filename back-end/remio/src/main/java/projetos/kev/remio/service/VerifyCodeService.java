package projetos.kev.remio.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void verifyCode (CodeVerifyDTO codeVerifyDTO){
        var code = verifyCodeRepository.findByCode(codeVerifyDTO.code());
        if (code != null) throw new RuntimeException("Código invalido");
    }
}
