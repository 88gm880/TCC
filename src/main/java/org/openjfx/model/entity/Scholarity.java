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
@Table(name = "scholarity")
@NoArgsConstructor @AllArgsConstructor
public class Scholarity {

    @Id
    @Column(
            name = "scholarity_id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "school_name")
    private String schoolName;

    @NotNull
    @Column(name = "school_shift")
    private String schoolShift;

    @NotNull
    @Column(name = "grade")
    private String grade;

    @NotNull
    @Column(name = "learning_difficulty")
    private boolean learningDifficulty;

    @Column(name = "learning_difficulty_obs")
    private String learningDifficultyObs;

    @Column(name = "scholarity_obs")
    private String scholarityObs;

    @OneToOne
    @JoinColumn(name = "st_id", referencedColumnName = "st_id")
    private Student student;
}
