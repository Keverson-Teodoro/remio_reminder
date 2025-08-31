package projetos.kev.remio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetos.kev.remio.DTO.ReminderRequestDto;
import projetos.kev.remio.model.entity.Reminder;
import projetos.kev.remio.service.ReminderService;

import java.util.List;

@RestController
@RequestMapping("/reminder")
@CrossOrigin(origins = "*")
public class ReminderController {

    @Autowired
    ReminderService reminderService;


    @PostMapping
    public ResponseEntity<?> newReminder(@RequestBody ReminderRequestDto reminder){
        try{
            reminderService.newReminder(reminder);
            return ResponseEntity.ok(reminder);

        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


    @GetMapping
    public List<Reminder> allReminders(){
        return reminderService.reminderList();
    }


    @DeleteMapping
    public void deleteRminder (String id){
        reminderService.deleteReminder(id);
    }



}
