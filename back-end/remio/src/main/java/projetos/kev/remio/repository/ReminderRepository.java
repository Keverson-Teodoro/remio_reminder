package projetos.kev.remio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetos.kev.remio.model.entity.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, String> {
}
