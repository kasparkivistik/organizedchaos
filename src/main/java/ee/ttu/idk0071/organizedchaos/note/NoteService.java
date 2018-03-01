package ee.ttu.idk0071.organizedchaos.note;

import org.springframework.stereotype.Service;

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
        return (List<Note>) noteRepository.findAll();
    }

    public Note getNoteById(long id) {
        return noteRepository.getNoteById(id);
    }
}
