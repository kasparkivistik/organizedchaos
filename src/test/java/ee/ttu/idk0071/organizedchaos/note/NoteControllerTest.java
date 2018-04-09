package ee.ttu.idk0071.organizedchaos.note;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ee.ttu.idk0071.organizedchaos.user.User;
import org.apache.catalina.filters.CorsFilter;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    private static final long TEST_NOTE_ID1 = 1;
    private static final long TEST_NOTE_ID2 = 2;
    private static final long TEST_USER_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    private Note sampleNote1;
    private Note sampleNote2;

    private User sampleUser1Id;

    @Before
    public void setup() {
        /*sampleNote1 = new Note();
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
        sampleNote1.setUser(sampleUser1);

        List<Note> sampleNotes = new ArrayList<>();
        sampleNotes.add(sampleNote1);
        sampleNotes.add(sampleNote2);


        given(noteService.getAllNotes()).willReturn(sampleNotes);
        given(noteService.getNotesByUser(sampleUser1)).willReturn(sampleNotes);
        //given(this.notes.findOne(TEST_NOTE_ID)).willReturn(sampleNote);*/
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(noteController)
                .addFilters(new CorsFilter())
                .build();
        
    }

    @Test
    public void testGetAllNotesSuccess() throws Exception {
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

        sampleUser1Id = new User();
        sampleUser1Id.setId(TEST_USER_ID);
        sampleNote1.setUser(sampleUser1Id);

        List<Note> sampleNotes = new ArrayList<>();
        sampleNotes.add(sampleNote1);
        sampleNotes.add(sampleNote2);

        Mockito.when(noteService.getAllNotes()).thenReturn(sampleNotes);
        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1L)))
                .andExpect(jsonPath("$[0].content", is("Content")))
                .andExpect(jsonPath("$[0].name", is("Title")))
                .andExpect(jsonPath("$[0].complete", is(true)))
                .andExpect(jsonPath("$[0].user_id", is(1L)))
                .andExpect(jsonPath("$[1].id", is(2L)))
                .andExpect(jsonPath("$[1].content", is("Content2")))
                .andExpect(jsonPath("$[1].name", is("Title2")))
                .andExpect(jsonPath("$[1].complete", is(false)));
        Mockito.verify(noteService, Mockito.times(1)).getAllNotes();
        verifyNoMoreInteractions(noteService);
        Assert.assertEquals(noteService.getAllNotes(), sampleNotes);
    }

    @Test
    public void testSaveNote() throws Exception {

    }

    @Test
    public void testDeleteNote() throws Exception {

    }

    @Test
    public void testGetNotesByUser() throws Exception {

    }

}

