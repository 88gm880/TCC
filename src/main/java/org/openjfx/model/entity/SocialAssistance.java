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
@Table(name = "social_assistence")
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull
    @Column(name = "bolsa_familia")
    private boolean bolsaFamilia;

    @Column(name = "bolsa_familia_obs")
    private String bolsaFamiliaObs;

    @NotNull
    @Column(name = "other_assistances")
    private boolean otherAssistances;

    @Column(name = "other_assistances_obs")
    private String otherAssistancesObs;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Override
    public String toString() {
        return "{" +
                "nis='" + nis + "',\n" +
                ", cras='" + cras + "',\n" +
                ", bolsaFamilia=" + bolsaFamilia + ",\n" +
                ", bolsaFamiliaObs='" + bolsaFamiliaObs + "',\n" +
                ", otherAssistances=" + otherAssistances +
                ", otherAssistancesObs='" + otherAssistancesObs + "'\n" +
                '}';
    }
}
