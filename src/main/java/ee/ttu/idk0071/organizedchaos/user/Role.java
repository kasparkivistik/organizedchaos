package ee.ttu.idk0071.organizedchaos.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToMany
    private Set<User> users;
}
