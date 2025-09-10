package projetos.kev.remio.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();

        return new Jackson2JsonMessageConverter();
    }
}
