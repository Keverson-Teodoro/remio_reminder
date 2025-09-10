package projetos.kev.remio.producer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import projetos.kev.remio.DTO.EmailRequestDTO;
import projetos.kev.remio.model.entity.User;


@Component
public class MailProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.email.name}")
    private String routenKey;



    public void welcomeEmail(User user){

        var emailTo = new EmailRequestDTO();

        emailTo.setUserReceiverId(user.getId());
        emailTo.setSubject("Cadastro realizado com sucesso");
        emailTo.setText("Bem vindo ao remio, " + user.getUsername());
        emailTo.setMailTo(user.getEmail());

        rabbitTemplate.convertAndSend("", routenKey, emailTo);

    }




}
