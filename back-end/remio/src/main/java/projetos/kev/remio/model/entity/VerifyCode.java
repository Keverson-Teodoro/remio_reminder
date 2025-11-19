package projetos.kev.remio.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "verify_code")
public class VerifyCode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "code")
    private Integer code;

    @Column(name = "code_send_date")
    private LocalDateTime sendCodeDate;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;
}
