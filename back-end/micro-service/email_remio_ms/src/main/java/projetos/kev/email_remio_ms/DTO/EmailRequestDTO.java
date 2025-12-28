package projetos.kev.email_remio_ms.DTO;



public record EmailRequestDTO (String userReceiverId, String mailTo,String subject, String text ) {
}
