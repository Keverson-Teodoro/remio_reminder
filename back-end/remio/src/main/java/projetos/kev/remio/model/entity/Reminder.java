package projetos.kev.remio.model.entity;


import jakarta.persistence.*;
import lombok.*;


@Table(name = "reminders")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reminder {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;





//    @ManyToOne
//    @JoinColumn(name = "id_user")
//    private User user;


    public Reminder(String description) {
    }
}
