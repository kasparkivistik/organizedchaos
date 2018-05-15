package ee.ttu.idk0071.organizedchaos.calendar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Long> {
    List<Calendar> findAll();

    List<Calendar> findAllByUserId(Long userId);
}
