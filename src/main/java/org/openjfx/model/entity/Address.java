package org.openjfx.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Setter @Getter
@Table(name = "ADDRESS")
@NoArgsConstructor @AllArgsConstructor
public class Address {

    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;

    private String number;

    private String district;

    private String complement;

    @OneToOne
    @JoinColumn(name = "COD_STUDENT", referencedColumnName = "COD_STUDENT")
    private Student student;

}
