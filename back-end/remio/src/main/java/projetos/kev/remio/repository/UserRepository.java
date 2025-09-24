package projetos.kev.remio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import projetos.kev.remio.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<UserDetails> findByUsername(String username);

    @Query("SELECT u from User u where u.username = :username ")
    User findUserByUsername (@Param("username") String username);

    User findByEmail(String email);
}
