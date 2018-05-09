package ee.ttu.idk0071.organizedchaos.note;

import ee.ttu.idk0071.organizedchaos.user.User;
import ee.ttu.idk0071.organizedchaos.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;

    public NoteService(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    public List<Note> getAllNotes() {
        long userID = userService.findCurrentUserId();
        List<Note> notes = noteRepository.findAllByUserId(userID);
        Collections.reverse(notes);
        return notes;
    }

    public void deleteNote(long id) {
        noteRepository.delete(id);
    }

    public Note saveNote(Note note) {
        User user = new User();
        user.setId(userService.findCurrentUserId());
        note.setUser(user);
        return noteRepository.save(note);
    }

    public List<Note> findAllByUserId(long userId) {
        return noteRepository.findAllByUserId(userId);
    }

    public Note getNoteById(long id) {
        return noteRepository.findOne(id);
    }
}
