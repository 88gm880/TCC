package org.openjfx.model.entity;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Valid
@Entity
@Builder
@Getter @Setter
@Table(name = "STUDENT")
@NoArgsConstructor @AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "st_id",
            updatable = false
    )
    private Integer id;

    @SequenceGenerator(
            name = "cod_sequence",
            sequenceName = "cod_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cod_sequence"
    )
    @Column(
            name = "cod_student",
            updatable = false
    )
    private Integer codStudent;

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

    /*@OneToOne(mappedBy = "student")
    private Health health;

    @OneToOne(mappedBy = "student")
    private SocialAssistance socialAssistance;
*/

}
