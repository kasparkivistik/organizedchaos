package ee.ttu.idk0071.organizedchaos.note;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAll();

    List<Note> findAllByUser(Long userId);
}
