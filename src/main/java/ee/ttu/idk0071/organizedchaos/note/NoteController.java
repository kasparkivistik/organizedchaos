package ee.ttu.idk0071.organizedchaos.note;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "api/notes")
public class NoteController {

    @Resource
    private NoteService noteService;

    @GetMapping(value = "")
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public void deleteNote(@PathVariable("id") long id) {
        noteService.deleteNote(id);
    }

    @PostMapping(value = "save")
    public ResponseEntity saveNote(@RequestBody Note note) {
        noteService.saveNote(note);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "setComplete")
    public ResponseEntity setComplete(@RequestParam("complete") boolean complete, @RequestParam("id") long id) {
        noteService.setComplete(complete, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "user/{user}")
    public ResponseEntity<List<Note>> getNotesByUser(@PathVariable("user") long userId) {
        return new ResponseEntity<>(noteService.getNotesByUser(userId), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") long id) {
        return new ResponseEntity<>(noteService.getNoteById(id), HttpStatus.OK);
    }

}
