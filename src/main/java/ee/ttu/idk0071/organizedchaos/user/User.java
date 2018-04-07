package ee.ttu.idk0071.organizedchaos.user;

import ee.ttu.idk0071.organizedchaos.note.Note;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    @OneToMany
    private List<Note> notes;
    private UserRoles role;
}
