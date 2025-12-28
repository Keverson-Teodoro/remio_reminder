package projetos.kev.email_remio_ms.consumers;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import projetos.kev.email_remio_ms.DTO.EmailRequestDTO;
import projetos.kev.email_remio_ms.model.entity.EmailModel;
import projetos.kev.email_remio_ms.service.EmailService;

@Component
public class QueueConsumer {

    @Autowired
    EmailService emailService;


    @RabbitListener(queues = "${broker.queue.email.name}")
    public void queueListener(@Payload EmailRequestDTO emailDTO){

        System.out.println(emailDTO);

        var email = new EmailModel();
        email.setEmailTo(emailDTO.mailTo());
        email.setSubject(emailDTO.subject());
        email.setText(emailDTO.text());
        email.setUserReceiverId(emailDTO.userReceiverId());
//        BeanUtils.copyProperties(email, emailDTO);
        emailService.sendEmail(email);


    }
}
