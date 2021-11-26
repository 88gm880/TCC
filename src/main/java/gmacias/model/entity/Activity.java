package gmacias.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Valid
@Entity
@Builder
@Getter @Setter
@Table(name = "activity")
@NoArgsConstructor @AllArgsConstructor
public class Activity {

    @Id
    @Column(
            name = "activity_id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name="activity_users", joinColumns=
            {@JoinColumn(name="activity_id")}, inverseJoinColumns=
            {@JoinColumn(name="user_id")})
    private List<User> activityUsers = new ArrayList<>();
}
