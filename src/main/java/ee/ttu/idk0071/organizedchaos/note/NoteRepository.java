package ee.ttu.idk0071.organizedchaos.note;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAll();

    @Query("SELECT n FROM Note n WHERE n.user=:userId ORDER BY n.createdAt DESC")
    List<Note> findAllByUserId(Long userId);
}
