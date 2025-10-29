package projetos.kev.remio.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetos.kev.remio.DTO.ReminderRequestDto;
import projetos.kev.remio.model.entity.Reminder;
import projetos.kev.remio.model.entity.User;
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
        Reminder reminder = new Reminder();

        if(user == null) throw new RuntimeException("Usuario não encontrado");

        BeanUtils.copyProperties(reminderRequestDto, reminder);
        user.getUserReminders().add(reminder);


        reminderRepository.save(reminder);
    }


    public List<Reminder> reminderList(String username){
//
        User user = userRepository.findUserByUsername(username);
        if (user == null) throw new RuntimeException("Usuário não encontrado");

        return user.getUserReminders();
    }


    public void deleteReminder(String id){
        reminderRepository.deleteById(id);
    }

    public Reminder editReminder(String id, ReminderRequestDto reminderRequestDto){

        Reminder reminder = reminderRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("Não encontramos esse lembrete na base de dados"));

        reminder.setDescription(reminderRequestDto.description());
        reminderRepository.save(reminder);
        return reminder;
    }


}
