package ee.ttu.idk0071.organizedchaos.note;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = "notes/add", method = RequestMethod.POST, consumes = "application/json")
    public Note addNote(Note note) {
        return noteService.addNote(note);
    }

    @RequestMapping(value="/notes", method=RequestMethod.GET)
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @RequestMapping(value = "/notes/{id}", method=RequestMethod.GET)
    public Note getNote(@PathVariable("id") long id) {
        return noteService.getNoteById(id);
    }
}
