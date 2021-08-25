package org.openjfx.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Setter @Getter
@Table(name = "social_assistence")
@NoArgsConstructor @AllArgsConstructor
public class SocialAssistance {

    @Id
    @Column(
            name = "social_assistence_id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nis;

    private String cras;

    //TODO bolsa familia e outras assistencias

    /*@OneToOne
    @JoinColumn(name = "cod_student", referencedColumnName = "cod_student")
    private Student student;*/

}
