package projetos.kev.remio.model.entity;


import jakarta.persistence.*;
import lombok.*;
import projetos.kev.remio.model.enums.ReminderStatus;

import javax.print.DocFlavor;


@Table(name = "reminders")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reminder {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReminderStatus status;

    @JoinColumn(name = "id_usuario")
    @ManyToOne
    private User user;

    public Reminder(String description, ReminderStatus status, User user) {
        this.description = description;
        this.status = status;
        this.user = user;
    }
}
