package projetos.kev.remio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projetos.kev.remio.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
