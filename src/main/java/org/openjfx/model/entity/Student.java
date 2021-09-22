package org.openjfx.model.entity;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Valid
@Entity
@Builder
@Getter @Setter
@Table(name = "student")
@NoArgsConstructor @AllArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "st_id",
            updatable = false
    )
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(name = "st_name")
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

    /*@OneToOne(mappedBy = "student")
    private Address address;

    @OneToOne(mappedBy = "student")
    private Habitation habitation;

    @OneToOne(mappedBy = "student")
    private Health health;

    @OneToOne(mappedBy = "student")
    private SocialAssistance socialAssistance;

    @OneToOne(mappedBy = "student")
    private Scholarity scholarity;*/

    @Override
    public String toString() {
        return "Student{ \n" +
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
