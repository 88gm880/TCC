package org.openjfx.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@Entity
@Builder
@Setter
@Getter
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
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

    //@NotNull
    @Column(name = "complement")
    private String complement;

    //@NotNull
    @Column(name = "reference")
    private String reference;

    @OneToOne
    @JoinColumn(name = "st_id", referencedColumnName = "st_id")
    private Student student;

    @Override
    public String toString() {
        return "{" +
                ", street='" + street + "',\n" +
                ", number='" + number + "',\n" +
                ", district='" + district + "',\n" +
                ", complement='" + complement + "',\n" +
                ", reference='" + reference + "'\n" +
                '}';
    }
}
