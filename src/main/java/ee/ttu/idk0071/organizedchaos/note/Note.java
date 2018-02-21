package ee.ttu.idk0071.organizedchaos.note;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String content;
    private String name;
    private boolean complete;
}
