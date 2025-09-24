package projetos.kev.remio.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailRequestDTO {


    private String userReceiverId;
    private String mailTo;
    private String subject;
    private String text;
}
