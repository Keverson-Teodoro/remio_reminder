package projetos.kev.remio.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projetos.kev.remio.model.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewPasswordRequestDto {

    private Integer code;
    private String password;
}