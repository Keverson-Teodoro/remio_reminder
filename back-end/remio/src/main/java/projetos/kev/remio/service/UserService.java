package projetos.kev.remio.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projetos.kev.remio.DTO.UserRegisterDTO;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.model.enums.UserRole;
import projetos.kev.remio.producer.MailProducer;
import projetos.kev.remio.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailProducer mailProducer;



    @Transactional
    public void salvar(UserRegisterDTO userRegisterDTO){

        userRepository.findAll().forEach(u -> {
            if(userRegisterDTO.username().equals(u.getUsername())){
                throw new RuntimeException("Usuário ja cadastrado");
            }
        });

        User user = new User();

        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.ADMIN);

        userRepository.save(user);
        mailProducer.welcomeEmail(user);
    }


    public UserDetails findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow( () -> new RuntimeException("Não encontramos"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
