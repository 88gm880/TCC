package org.openjfx.model.entity;

import lombok.*;

import javax.persistence.*;

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

    private boolean physicalIllness;

    private boolean mentalIllness;

    private boolean medicalMonitoring;

    //private boolean c; //TODO medicamento continuo

    /*@OneToOne
    @JoinColumn(name = "cod_student", referencedColumnName = "cod_student")
    private Student student;*/

}
