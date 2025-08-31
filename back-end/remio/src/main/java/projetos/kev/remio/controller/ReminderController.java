package projetos.kev.remio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetos.kev.remio.DTO.ReminderRequestDto;
import projetos.kev.remio.service.ReminderService;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

    @Autowired
    ReminderService reminderService;

    public ResponseEntity<?> newReminder(ReminderRequestDto reminder){
        try{
            reminderService.newReminder(reminder);
            return ResponseEntity.ok(reminder);

        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }



}
