package ee.ttu.idk0071.organizedchaos.user;

import ee.ttu.idk0071.organizedchaos.note.Note;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
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
    private Set<Role> roles;
    @OneToMany
    private List<Note> notes;
}
