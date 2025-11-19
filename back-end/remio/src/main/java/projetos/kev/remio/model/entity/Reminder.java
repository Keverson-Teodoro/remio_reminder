package projetos.kev.remio.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import projetos.kev.remio.model.enums.ReminderStatus;


@Table(name = "reminder")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_reminder")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReminderStatus status;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    public Reminder(String description, ReminderStatus status, User user) {
        this.description = description;
        this.status = status;
        this.user = user;
    }
}
