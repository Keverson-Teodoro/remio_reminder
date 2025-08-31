package projetos.kev.remio.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetos.kev.remio.DTO.ReminderRequestDto;
import projetos.kev.remio.model.entity.Reminder;
import projetos.kev.remio.repository.ReminderRepository;

import java.util.List;

@Service
public class ReminderService {

    @Autowired
    ReminderRepository reminderRepository;

    public void newReminder(ReminderRequestDto reminderRequestDto){

        Reminder reminder = new Reminder();
        reminder.setDescription(reminderRequestDto.description());

        reminderRepository.save(reminder);
    }


    public List<Reminder> reminderList(){
        return reminderRepository.findAll();
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
