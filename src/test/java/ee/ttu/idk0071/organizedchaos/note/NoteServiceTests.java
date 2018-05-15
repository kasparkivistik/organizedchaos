package ee.ttu.idk0071.organizedchaos.note;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    //@MockBean
    //private UserService userService;

    //@InjectMocks
    //private UserController userController;

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
        sampleUser1.setUsername("keit");
        sampleUser1.setPassword("123");
        sampleUser1.setEmail("keit@keit.ee");

        sampleNotes = new ArrayList<>();
        sampleNotes.add(sampleNote1);
        sampleNotes.add(sampleNote2);

        sampleNote1.setUser(sampleUser1);

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
        mockMvc.perform(get("api/notes"))
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
        //when(noteService.getNoteById(TEST_NOTE_ID1)).thenReturn(sampleNote1);
        mockMvc.perform(post("api/notes/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(sampleNote1)))
                .andExpect(status().isCreated());
        verify(noteService, times(1)).saveNote(sampleNote1);
        verifyNoMoreInteractions(noteService);
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
        when(noteService.findAllByUserId(sampleUser1.getId())).thenReturn(sampleNotes);
        mockMvc.perform(get("api/notes/{user}", sampleUser1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("keit")));
        verify(noteService, times(1)).findAllByUserId(sampleUser1.getId());
        verifyNoMoreInteractions(noteService);
    }

    @Test
    public void testGetNotesByUserFail404NotFound() throws Exception {
        when(noteService.findAllByUserId(sampleUser1.getId())).thenReturn(null);
        mockMvc.perform(get("api/notes/{user}", 1))
                .andExpect(status().isNotFound());
        verify(noteService, times(1)).findAllByUserId(sampleUser1.getId());
        verifyNoMoreInteractions(noteService);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

