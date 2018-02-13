package ee.ttu.idk0071.organizedchaos.note;

import lombok.Data;

import java.util.Date;

@Data
public class Note {

    private String content;
    private String name;
    private Date dateCreated;
}
