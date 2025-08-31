package projetos.kev.remio.service;


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

        reminderRepository.save(new Reminder(reminderRequestDto.description()));
    }


    public List<Reminder> reminderList(){
        return reminderRepository.findAll();
    }



}
