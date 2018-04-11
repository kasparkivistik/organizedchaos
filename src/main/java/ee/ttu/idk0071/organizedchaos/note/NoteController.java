package ee.ttu.idk0071.organizedchaos.note;

import ee.ttu.idk0071.organizedchaos.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "notes")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @DeleteMapping(value = "{id}")
    public void deleteNote(@PathVariable("id") long id) {
        noteService.deleteNote(id);
    }

    @PostMapping(value = "save")
    public ResponseEntity<Note> saveNote(@RequestBody Note note) {
         noteService.saveNote(note);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "user/{user}")
    public List<Note> getNotesByUser(@PathVariable("user") User user) {
        return noteService.getNotesByUser(user);
    }

    @GetMapping(value = "{id}")
    public Note getNoteById(@PathVariable("id") long id) {
        return noteService.getNoteById(id);
    }
}
