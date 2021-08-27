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

    @NotNull
    @Column(name = "nis")
    private String nis;

    @NotNull
    @Column(name = "cras")
    private String cras;

    //TODO bolsa familia e outras assistencias

    @OneToOne
    @JoinColumn(name = "cod_student", referencedColumnName = "cod_student")
    private Student student;

}
