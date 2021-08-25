package org.openjfx.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Setter @Getter
@Table(name = "address")
@NoArgsConstructor @AllArgsConstructor
public class Address {

    @Id
    @Column(
            name = "address_id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "number")
    private String number;

    @NotNull
    @Column(name = "district")
    private String district;

    @NotNull
    @Column(name = "complement")
    private String complement;

    @OneToOne
    @JoinColumn(name = "cod_student", referencedColumnName = "cod_student")
    private Student student;

}
