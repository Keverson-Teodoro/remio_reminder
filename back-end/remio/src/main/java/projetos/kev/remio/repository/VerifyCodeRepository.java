package projetos.kev.remio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projetos.kev.remio.model.entity.VerifyCode;

public interface VerifyCodeRepository extends JpaRepository<VerifyCode, String> {


    VerifyCode findByCode(Integer code);
}
