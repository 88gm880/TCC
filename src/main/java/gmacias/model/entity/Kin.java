package gmacias.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Kin {

    private String name;

    private Integer age;

    private String kinship;

    private String attended;

    private String scholarity;

    private String occupation;

    private Double income;

    /*@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;*/

}
