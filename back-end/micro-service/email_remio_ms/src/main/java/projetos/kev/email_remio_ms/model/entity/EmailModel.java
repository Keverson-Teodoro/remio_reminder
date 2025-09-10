package projetos.kev.email_remio_ms.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projetos.kev.email_remio_ms.model.enums.EmailStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_table")
public class EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_email")
    private String emailId;

    private String userReceiverId;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendEmailDate;
    private String emailFrom;

    @Enumerated(EnumType.STRING)
    private EmailStatus emailStatus;
}
