package ee.ttu.idk0071.organizedchaos.calendar;

import ee.ttu.idk0071.organizedchaos.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "events")
public class CalendarController {

    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) { this.calendarService = calendarService; }

    @GetMapping
    public List<Calendar> getAllEvents() {return calendarService.getAllEvents(); }

    @DeleteMapping(value = "{id}")
    public void deleteEvent(@PathVariable("id") long id) { calendarService.deleteEvent(id);}

    @PostMapping(value = "save", consumes="application/json; charset=UTF-8")
    public ResponseEntity<Calendar> saveEvent(@RequestBody Calendar event) {
        calendarService.saveEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "user/{user}")
    public List<Calendar> getEventsByUser(@PathVariable User user) {
        return calendarService.getEventsByUser(user);
    }

    @GetMapping(value = "{id}")
    public Calendar getEventById(@PathVariable("id") long id) {
        return calendarService.getEventById(id);
    }
}
