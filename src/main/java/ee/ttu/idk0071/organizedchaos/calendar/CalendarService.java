package ee.ttu.idk0071.organizedchaos.calendar;

import ee.ttu.idk0071.organizedchaos.user.User;
import ee.ttu.idk0071.organizedchaos.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserService userService;

    public CalendarService(CalendarRepository calendarRepository, UserService userService) {
        this.calendarRepository = calendarRepository;
        this.userService = userService;
    }

    public List<Calendar> getAllEvents() {
        User user = userService.findCurrentUserId();
        long userID = user.getId();
        List<Calendar> events = calendarRepository.findAllByUserId(userID);
        Collections.reverse(events);
        return events;
    }

    public void deleteEvent(long id) {
        calendarRepository.delete(id);
    }

    public Calendar saveEvent(Calendar event) {
        User user = new User();
        user.setId(userService.findCurrentUserId().getId());
        event.setUser(user);
        return calendarRepository.save(event);
    }

    public List<Calendar> getEventsByUser(User user) {
        return (List<Calendar>) calendarRepository.findOne(user.getId());
    }

    public Calendar getEventById(long id) {
        return calendarRepository.findOne(id);
    }
}
