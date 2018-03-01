package ee.ttu.idk0071.organizedchaos.note;

import ee.ttu.idk0071.organizedchaos.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String content;
    private String name;
    private boolean complete;
    @ManyToOne
    private User user;
}
