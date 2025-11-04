package projetos.kev.remio.producer;


import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import projetos.kev.remio.DTO.EmailRequestDTO;
import projetos.kev.remio.model.entity.User;

import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class MailProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.email.name}")
    private String routenKey;



    public void welcomeEmail(User user){

        EmailRequestDTO emailTo = new EmailRequestDTO();

        Map<String, Object> model = new HashMap<>();
        model.put("nome", user.getUsername());

        emailTo.setUserReceiverId(user.getId());
        emailTo.setSubject("Cadastro realizado com sucesso");
        emailTo.setText( user.getUsername() + ", Bem vindo ao remio, seu app de lembretes");
        emailTo.setMailTo(user.getEmail());
    
        if(emailTo.getMailTo() == null) throw new RuntimeException("Destinatario não pode ser vazio");

        rabbitTemplate.convertAndSend("", routenKey, emailTo);
    }
}
