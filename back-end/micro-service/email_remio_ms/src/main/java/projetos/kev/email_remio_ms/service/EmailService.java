package projetos.kev.email_remio_ms.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import projetos.kev.email_remio_ms.model.entity.EmailModel;
import projetos.kev.email_remio_ms.repository.EmailRepository;

import java.time.LocalDateTime;

@Service
public class EmailService {



    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender javaMailSender;


//    private final JavaMailSender javaMailSender;
//
//    public EmailService(JavaMailSender javaMailSender){
//        this.javaMailSender = javaMailSender;
//    }



    @Transactional
    public void sendEmail(EmailModel emailModel){
        try{
            emailModel.setSendEmailDate(LocalDateTime.now());
            emailModel.setEmailFrom("keverson.teodoro@youxlab.com.br");

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            message.setFrom(emailModel.getEmailFrom());
            javaMailSender.send(message);


        }catch (MailException mailException){
            throw new RuntimeException("Erro ao tentar enviar o email");
        }
        finally {
            emailRepository.save(emailModel);
        }
    }



}
