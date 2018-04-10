package ee.ttu.idk0071.organizedchaos.note;

import ee.ttu.idk0071.organizedchaos.user.User;
import org.apache.catalina.filters.CorsFilter;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteServiceTests {

    private static final long TEST_NOTE_ID1 = 1L;
    private static final long TEST_NOTE_ID2 = 2L;
    private static final long TEST_USER_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    private Note sampleNote1;
    private Note sampleNote2;

    private User sampleUser1;

    private List<Note> sampleNotes;

    @Before
    public void setup() {
        sampleNote1 = new Note();
        sampleNote1.setId(TEST_NOTE_ID1);
        sampleNote1.setContent("Content");
        sampleNote1.setName("Title");
        sampleNote1.setComplete(true);

        sampleNote2 = new Note();
        sampleNote2.setId(TEST_NOTE_ID2);
        sampleNote2.setContent("Content2");
        sampleNote2.setName("Title2");
        sampleNote2.setComplete(false);

        sampleUser1 = new User();
        sampleUser1.setId(TEST_USER_ID);
        sampleNote1.setUser(sampleUser1);

        sampleNotes = new ArrayList<>();
        sampleNotes.add(sampleNote1);
        sampleNotes.add(sampleNote2);

        given(noteService.getAllNotes()).willReturn(sampleNotes);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(noteController)
                .addFilters(new CorsFilter())
                .build();

    }

    @Test
    public void testGetAllNotesSuccess() throws Exception {

        Mockito.when(noteService.getAllNotes()).thenReturn(sampleNotes);
        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].content", Matchers.is("Content")))
                .andExpect(jsonPath("$[0].name", Matchers.is("Title")))
                .andExpect(jsonPath("$[0].complete", Matchers.is(true)))
                //.andExpect(jsonPath("$[0].user", Matchers.is(1)))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].content", Matchers.is("Content2")))
                .andExpect(jsonPath("$[1].name", Matchers.is("Title2")))
                .andExpect(jsonPath("$[1].complete", Matchers.is(false)));
        Mockito.verify(noteService, Mockito.times(1)).getAllNotes();
        verifyNoMoreInteractions(noteService);
        Assert.assertEquals(noteService.getAllNotes(), sampleNotes);
    }

    @Test
    public void testSaveNote() throws Exception {

    }

    @Test
    public void testDeleteNote() throws Exception {
        doNothing().when(noteService).deleteNote(sampleUser1.getId());
        mockMvc.perform(delete("/notes/{id}", sampleUser1.getId()))
                .andExpect(status().isOk());
        verify(noteService, times(1)).deleteNote(sampleUser1.getId());
        verifyNoMoreInteractions(noteService);
    }

    @Test
    public void testGetNotesByUser() throws Exception {

    }

}

