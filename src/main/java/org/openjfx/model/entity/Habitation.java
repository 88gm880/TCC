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
@Setter @Getter
@Table(name = "habitation")
@NoArgsConstructor @AllArgsConstructor
public class Habitation {

    @Id
    @Column(
            name = "habitation_id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "residence_kind")
    private String residenceKind;

    @NotNull
    @Column(name = "private_eletricity")
    private boolean privateEletricity;

    @NotNull
    @Column(name = "private_piped_water")
    private boolean privatePipedWater;

    @NotNull
    @Column(name = "sewer")
    private boolean sewer;

    @NotNull
    @Column(name = "building_type")
    private String buildingType;

    @NotNull
    @Column(name = "rooms")
    private Integer rooms;

    @NotNull
    @Column(name = "bedrooms")
    private Integer bedrooms;

    @OneToOne
    @JoinColumn(name = "cod_student", referencedColumnName = "cod_student")
    private Student student;

}
