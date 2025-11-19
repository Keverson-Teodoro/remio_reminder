package projetos.kev.remio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.model.entity.VerifyCode;

public interface VerifyCodeRepository extends JpaRepository<VerifyCode, String> {


    VerifyCode findByCode(Integer code);

    @Query(value = "SELECT * from verify_code where code = :code", nativeQuery = true)
    User findUserByCode (@Param("code") Integer code);
}
