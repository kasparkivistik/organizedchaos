package ee.ttu.idk0071.organizedchaos.user;

import ee.ttu.idk0071.organizedchaos.note.Note;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String password;
    @Email
    private String email;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    @JoinTable(name = "userRole", joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;
    @ManyToOne
    @JoinTable(name = "userNotes")
    private ArrayList<Note> notes;
}
