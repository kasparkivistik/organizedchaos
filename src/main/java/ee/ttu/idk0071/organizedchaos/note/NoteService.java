package ee.ttu.idk0071.organizedchaos.note;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        List<Note> notes = (List<Note>) noteRepository.findAll();
        Collections.reverse(notes);
        return notes;
    }

    public Note getNoteById(long id) {
        return noteRepository.getNoteById(id);
    }

    public void deleteNote(long id) {
        noteRepository.delete(id);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }
}
