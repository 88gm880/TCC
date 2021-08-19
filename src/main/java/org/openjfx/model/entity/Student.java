package org.openjfx.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openjfx.model.Address;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "STUDENT")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "ST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ST_NAME")
    private String name;

    private LocalDate birthday;

    private int age;

    private String naturality;

    private String fatherName;

    private String motherName;

    //private Address address;

    private String phone;

    private String messagePhone;

    public String toSqlString() {
        return "'" + name + "', " +
                "'" + birthday + "', " +
                "'" + age + "', " +
                "'" + naturality + "', " +
                "'" + fatherName + "', " +
                "'" + motherName + "', " +
                "'" + phone + "', " +
                "'" + messagePhone + "' ";
    }
}
