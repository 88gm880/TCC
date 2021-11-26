package gmacias.model.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@Entity
@Setter
@NoArgsConstructor
@Table(name = "login")
public class Login {

    @Id
    @Column(
            name = "login_id",
            updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "login_user")
    private String loginUser;

    @NotNull
    @Column(name = "login_pwd")
    private String loginPassword;

    @NotNull
    @Column(name = "login_type")
    private String loginType;
}
