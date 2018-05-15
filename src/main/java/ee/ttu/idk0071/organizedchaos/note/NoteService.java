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
        long userId = userService.findCurrentUserId().getId();
        System.out.println("note repo" + noteRepository.findAll());
        System.out.println(getNotesByUser(userId));
        List<Note> notes = getNotesByUser(userId);
        Collections.reverse(notes);
        return notes;
    }

    public void deleteNote(long id) {
        noteRepository.delete(id);
    }

    public Note saveNote(Note note) {
        User user = userService.findCurrentUserId();
        note.setUser(user);
        return noteRepository.save(note);
    }

    public List<Note> getNotesByUser(Long userId) {
        User user = userService.findById(userId);
        return noteRepository.findAllByUserId(user);
    }

    public Note getNoteById(long id) {
        return noteRepository.findOne(id);
    }
}
