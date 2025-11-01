package projetos.kev.remio.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import projetos.kev.remio.DTO.ReminderRequestDto;
import projetos.kev.remio.model.entity.Reminder;
import projetos.kev.remio.model.entity.User;
import projetos.kev.remio.model.enums.ReminderStatus;
import projetos.kev.remio.repository.ReminderRepository;
import projetos.kev.remio.repository.UserRepository;

import java.util.List;

@Service
public class ReminderService {

    @Autowired
    ReminderRepository reminderRepository;

    @Autowired
    UserRepository userRepository;

    public void newReminder(ReminderRequestDto reminderRequestDto, String username){

        User user = userRepository.findUserByUsername(username);
        Reminder reminder = new Reminder(reminderRequestDto.description(), ReminderStatus.PENDING, user);

        if(user == null) throw new RuntimeException("Usuario não encontrado");
        reminderRepository.save(reminder);
    }

    public List<Reminder> reminderList(String username){
//
        User user = userRepository.findUserByUsername(username);
        if (user == null) throw new RuntimeException("Usuário não encontrado");

        return reminderRepository.findByUserIdUser(user.getId());
    }


    public void deleteReminder(String id){
        reminderRepository.deleteById(id);

    }

    public void finishReminder(String id){
        Reminder reminder = reminderRepository.findById(id).orElseThrow( () -> new RuntimeException("Lembrete não encontrado"));
        reminder.setStatus(ReminderStatus.FINISH);
    }

    public Reminder editReminder(String id, ReminderRequestDto reminderRequestDto){

        Reminder reminder = reminderRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("Não encontramos esse lembrete na base de dados"));

        reminder.setDescription(reminderRequestDto.description());
        reminderRepository.save(reminder);
        return reminder;
    }
}
