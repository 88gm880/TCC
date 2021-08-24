package org.openjfx.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "STUDENT")
@Builder @Getter
@NoArgsConstructor @AllArgsConstructor
public class Student {

    @Id
    @Column(name = "ST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "COD_STUDENT")
    @SequenceGenerator(
            name = "cod_sequence",
            sequenceName = "cod_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cod_sequence"
    )
    private long codStudent;

    @Column(name = "ST_NAME")
    private String name;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "AGE")
    private int age;

    @Column(name = "NATURALITY")
    private String naturality;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @Column(name = "MOTHER_NAME")
    private String motherName;

    @OneToOne(mappedBy = "student")
    private Address address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "MESSAGE_PHONE")
    private String messagePhone;
}
