package ee.ttu.idk0071.organizedchaos.role;


import ee.ttu.idk0071.organizedchaos.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    private List<User> users;

}

