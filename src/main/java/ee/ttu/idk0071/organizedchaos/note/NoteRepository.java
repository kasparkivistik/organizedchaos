package ee.ttu.idk0071.organizedchaos.note;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

    Note getNoteById(long id);
}
