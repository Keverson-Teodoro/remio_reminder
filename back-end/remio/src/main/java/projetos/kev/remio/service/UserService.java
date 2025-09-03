package projetos.kev.remio.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projetos.kev.remio.DTO.UserRegisterDTO;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void salvar(UserRegisterDTO userRegisterDTO){
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }




}
