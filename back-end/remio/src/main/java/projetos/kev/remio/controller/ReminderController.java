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
public class ReminderController {

    @Autowired
    ReminderService reminderService;

    @PostMapping("/{username}")
    public ResponseEntity<?> newReminder(@RequestBody ReminderRequestDto reminder, @PathVariable("username") String username){
        try{
            reminderService.newReminder(reminder, username);
            return ResponseEntity.ok(reminder);

        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{username}")
    public List<Reminder> allReminders(@PathVariable("username") String username){
        return reminderService.reminderList(username);
    }

    @DeleteMapping("/{id}/{username}")
    public void deleteRminder (@PathVariable("id") String id){
        reminderService.deleteReminder(id);
    }

    @PatchMapping("/{id}")
    public Reminder editReminder(@PathVariable("id") String id, @RequestBody ReminderRequestDto reminder){
        return reminderService.editReminder(id, reminder);
    }



}
