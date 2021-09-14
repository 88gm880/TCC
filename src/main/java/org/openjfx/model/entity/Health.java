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
@Table(name = "health")
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "physical_obs")
    private String physicalObs;

    @NotNull
    @Column(name = "mental_illness")
    private boolean mentalIllness;

    @Column(name = "mental_obs")
    private String mentalObs;

    @NotNull
    @Column(name = "medical_monitoring")
    private boolean medicalMonitoring;

    @Column(name = "medical_obs")
    private String medicalObs;

    @NotNull
    @Column(name = "remedy")
    private boolean continuousRemedy;

    @Column(name = "remedy_obs")
    private String remedyObs;

    @OneToOne
    @JoinColumn(name = "st_id", referencedColumnName = "st_id")
    private Student student;

    @Override
    public String toString() {
        return "{" +
                "physicalIllness=" + physicalIllness + ",\n" +
                ", physicalObs='" + physicalObs + "',\n" +
                ", mentalIllness=" + mentalIllness + ",\n" +
                ", mentalObs='" + mentalObs + "',\n" +
                ", medicalMonitoring=" + medicalMonitoring + ",\n" +
                ", medicalObs='" + medicalObs + "',\n" +
                ", continuousRemedy=" + continuousRemedy + ",\n" +
                ", remedyObs='" + remedyObs + "'\n" +
                '}';
    }
}
