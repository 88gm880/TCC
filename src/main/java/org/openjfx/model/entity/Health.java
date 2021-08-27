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
@Table(name = "health")
@NoArgsConstructor @AllArgsConstructor
public class Health {

    @Id
    @Column(
            name = "health_id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "physical_illness")
    private boolean physicalIllness;

    @NotNull
    @Column(name = "mental_illness")
    private boolean mentalIllness;

    @NotNull
    @Column(name = "medical_monitoring")
    private boolean medicalMonitoring;

    @NotNull
    @Column(name = "remedy")
    private boolean continuousRemedy;

    @OneToOne
    @JoinColumn(name = "cod_student", referencedColumnName = "cod_student")
    private Student student;

}
