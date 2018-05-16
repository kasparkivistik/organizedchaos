package ee.ttu.idk0071.organizedchaos.user;

import ee.ttu.idk0071.organizedchaos.note.Note;
import org.apache.catalina.filters.CorsFilter;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserServiceTests {

    public static final long TEST_USER_ID1 = 1L;
    public static final long TEST_USER_ID2 = 2L;
    public static final long TEST_USER_ID3 = 3L;

    public static final String TEST_USER_EMAIL = "test1@test.ee";

    public static final long NOTE_ID1 = 1L;
    public static final long NOTE_ID2 = 2L;

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;
    private User user3;

    private List<User> sampleUsers;

    private Note sampleNote1;
    private Note sampleNote2;

    private List<Note> sampleNotes;

    @Before
    public void setup() {
        sampleNote1.setComplete(false);
        sampleNote1.setId(NOTE_ID1);
        sampleNote1.setContent("test1");
        sampleNote1.setUser(user1);

        sampleNote2.setComplete(true);
        sampleNote2.setId(NOTE_ID2);
        sampleNote2.setContent("test2");
        sampleNote2.setUser(user1);


        sampleNotes = new ArrayList<>();
        sampleNotes.add(sampleNote1);
        sampleNotes.add(sampleNote2);

        user1 = new User();
        user1.setId(TEST_USER_ID1);
        user1.setEmail(TEST_USER_EMAIL);
        user1.setPassword("123");
        //user1.setUsername("test1");
        user1.setNotes(sampleNotes);

        user2 = new User();
        user2.setId(TEST_USER_ID2);
        user2.setEmail("test2@test.ee");
        user2.setPassword("456");
        //user2.setUsername("test2");

        user3 = new User();
        user3.setId(TEST_USER_ID3);
        user3.setEmail("test3@test.ee");
        user3.setPassword("789");
        //user3.setUsername("test3");

        sampleUsers = new ArrayList<>();
        sampleUsers.add(user1);
        sampleUsers.add(user2);
        sampleUsers.add(user3);

        given(userService.getAllUsers()).willReturn(sampleUsers);

        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .addFilters(new CorsFilter())
                .build();
    }

    @Test
    public void testAddUser() throws Exception {

    }

    @Test
    public void testGetAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(sampleUsers);
        mockMvc.perform(get("api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].email", Matchers.is("test1@test.ee")))
                .andExpect(jsonPath("$[0].password", Matchers.is("123")))
                //.andExpect(jsonPath("$[0].complete", Matchers.is(true)))
                //.andExpect(jsonPath("$[0].user", Matchers.is(1)))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].content", Matchers.is("Content2")))
                .andExpect(jsonPath("$[1].name", Matchers.is("Title2")))
                .andExpect(jsonPath("$[1].complete", Matchers.is(false)));
        ;
        Mockito.verify(userService, Mockito.times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
        Assert.assertEquals(userService.getAllUsers(), sampleUsers);
    }

    @Test
    public void testGetUserById() throws Exception {

    }

    @Test
    public void testGetUserByEmail() throws Exception {

    }
}
