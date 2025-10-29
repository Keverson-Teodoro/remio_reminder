package projetos.kev.remio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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



    public VerifyCode validateEmail (VerifyCode verifyCode){

        VerifyCode verifyCodeObj = verifyCodeRepository.findByCode(verifyCode.getCode());
        if(verifyCodeObj == null) throw new RuntimeException("Código invalido!");
        return verifyCode;


    }


    public VerifyCode findByCode(Integer code){
        VerifyCode verifyCode = verifyCodeRepository.findByCode(code);

        if(verifyCode == null) throw new RuntimeException("Código inválido");
        return verifyCode;
    }
}
