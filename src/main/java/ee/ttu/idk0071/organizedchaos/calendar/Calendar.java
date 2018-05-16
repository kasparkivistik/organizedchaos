package ee.ttu.idk0071.organizedchaos.calendar;

import ee.ttu.idk0071.organizedchaos.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Calendar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String title;
    private Date start;
    private Date end;
    @ManyToOne
    private User user;
    private String textColor;
}
