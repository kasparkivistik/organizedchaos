package ee.ttu.idk0071.organizedchaos.note;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "notes")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(value = "add")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @DeleteMapping(value = "{id}")
    public void deleteNote(@PathVariable("id") long id) {
        noteService.deleteNote(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable("id") long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping(value = "save")
    public Note saveNote(@RequestBody Note note) {
        return noteService.saveNote(note);
    }
}
