package projetos.kev.remio.DTO;

import projetos.kev.remio.model.enums.UserRole;

public record UserRegisterDTO(String username, String email, String password, UserRole userRole) {
}
