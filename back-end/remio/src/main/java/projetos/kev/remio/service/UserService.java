package projetos.kev.remio.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projetos.kev.remio.DTO.UserRegisterDTO;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void salvar(UserRegisterDTO userRegisterDTO){

        userRepository.findAll().forEach(u -> {
            if(userRegisterDTO.username().equals(u.getUsername())){
                throw new RuntimeException("Usuário ja cadastrado");
            }
        });

        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public UserDetails findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow( () -> new RuntimeException("Não encontramos"));
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByUsername(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
    }
}
