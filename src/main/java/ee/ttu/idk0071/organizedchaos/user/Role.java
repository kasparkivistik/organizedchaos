package ee.ttu.idk0071.organizedchaos.user;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
