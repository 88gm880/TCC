package org.openjfx.model.entity;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
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
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Valid
@Entity
@Builder
@Getter @Setter
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "cod_student_uk", columnNames = "cod_student")
        }
)
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
    @Column(
            name = "cod_student",
            updatable = false,
            unique = true
    )
    private String codStudent;

    @NotNull
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
    @Column(name= "godfather")
    private boolean godfather;

    @NotNull
    @Column(name= "dead_father")
    private boolean deadFather;

    @NotNull
    @Column(name = "mother_name")
    private String motherName;

    @NotNull
    @Column(name= "godmother")
    private boolean godmother;

    @NotNull
    @Column(name= "dead_mother")
    private boolean deadMother;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "message_phone")
    private String messagePhone;

    @OneToOne(mappedBy = "student")
    private Address address;

    @OneToOne(mappedBy = "student")
    private Habitation habitation;

    @OneToOne(mappedBy = "student")
    private Health health;

    @OneToOne(mappedBy = "student")
    private SocialAssistance socialAssistance;

}
