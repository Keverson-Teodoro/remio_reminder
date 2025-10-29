package projetos.kev.remio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projetos.kev.remio.model.entity.Reminder;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, String> {


//    @Query("SELECT r FROM Reminder where r.user_id = :id")
//    List<Reminder> findByUserId(String id);
}
