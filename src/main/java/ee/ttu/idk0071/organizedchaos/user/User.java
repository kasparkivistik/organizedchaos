package ee.ttu.idk0071.organizedchaos.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    @JoinTable(name = "userRole", joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;
}
