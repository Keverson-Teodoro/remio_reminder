package projetos.kev.email_remio_ms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projetos.kev.email_remio_ms.model.entity.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, String> {
}
