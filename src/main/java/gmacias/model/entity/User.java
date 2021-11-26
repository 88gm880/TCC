package gmacias.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Valid
@Entity
@Builder
@Getter @Setter
@Table(name = "users")
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @Column(
            name = "user_id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(name = "user_name")
    private String name;

    @NotNull
    @Column(name = "birthday")
    private LocalDate birthday;

    @NotNull
    @Column(name = "age")
    private Integer age;

    @NotNull
    @Column(name = "sexo")
    private char sexo;

    @NotNull
    @Column(name = "naturality")
    private String naturality;

    @NotNull
    @Column(name = "father_name")
    private String fatherName;

    @NotNull
    @Column(name = "godfather")
    private boolean godfather;

    @NotNull
    @Column(name = "dead_father")
    private boolean deadFather;

    @NotNull
    @Column(name = "mother_name")
    private String motherName;

    @NotNull
    @Column(name = "godmother")
    private boolean godmother;

    @NotNull
    @Column(name = "dead_mother")
    private boolean deadMother;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "message_phone")
    private String messagePhone;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "attending_shift")
    private String attendingShift;

    @NotNull
    @Column(name = "referral_institution")
    private String referralInstitution;

    @NotNull
    @Column(name = "user_status")
    private boolean status;

    @Transient
    private boolean selected = false;

    @ManyToMany(mappedBy = "activityUsers")
    private List<Activity> activities = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Address address;
    @OneToOne(mappedBy = "user")
    private Habitation habitation;
    @OneToOne(mappedBy = "user")
    private Health health;
    @OneToOne(mappedBy = "user")
    private Scholarity scholarity;
    @OneToOne(mappedBy = "user")
    private SocialAssistance socialAssistance;


    @Override
    public String toString() {
        return "User{ \n" +
                "name='" + name + "',\n" +
                "birthday=" + birthday + ",\n" +
                "age=" + age + ",\n" +
                "sexo=" + sexo + ",\n" +
                "naturality='" + naturality + "',\n" +
                "fatherName='" + fatherName + "',\n" +
                "godfather=" + godfather + ",\n" +
                "deadFather=" + deadFather + ",\n" +
                "motherName='" + motherName + "',\n" +
                "godmother=" + godmother + ",\n" +
                "deadMother=" + deadMother + ",\n" +
                "phone='" + phone + "',\n" +
                "messagePhone='" + messagePhone + "',\n" +
                "startDate=" + startDate + ",\n" +
                "attendingShift='" + attendingShift + "',\n" +
                "referralInstitution='" + referralInstitution + "',\n" +
                /*"address=" + address + ",\n" +
                "habitation=" + habitation + ",\n" +
                "health=" + health + ",\n" +
                "socialAssistance=" + socialAssistance + ",\n" +
                "scholarity=" + scholarity + "\n" +*/
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }
}
