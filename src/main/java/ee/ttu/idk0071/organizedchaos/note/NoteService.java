package ee.ttu.idk0071.organizedchaos.note;

import ee.ttu.idk0071.organizedchaos.user.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        Collections.reverse(notes);
        return notes;
    }

    public void deleteNote(long id) {
        noteRepository.delete(id);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getNotesByUser(User user) {
        return noteRepository.findAllByUser(user.getId());
    }

    public Note getNoteById(long id) {
        return noteRepository.findOne(id);
    }
}
