package projetos.kev.remio.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
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

    @Column(name = "id_user")
    private User user;
}
