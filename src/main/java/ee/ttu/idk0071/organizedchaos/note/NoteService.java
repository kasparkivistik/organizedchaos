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

    List<Note> getAllNotes() {
        long userId = userService.findCurrentUserId().getId();
        List<Note> notes = getNotesByUser(userId);
        Collections.reverse(notes);
        return notes;
    }

    void deleteNote(long id) {
        noteRepository.delete(id);
    }

    void saveNote(Note note) {
        User user = userService.findCurrentUserId();
        note.setUser(user);
        noteRepository.save(note);
    }

    List<Note> getNotesByUser(Long userId) {
        return noteRepository.findAllByUserIdOrderByDateAsc(userId);
    }

    Note getNoteById(long id) {
        return noteRepository.findOne(id);
    }
}
