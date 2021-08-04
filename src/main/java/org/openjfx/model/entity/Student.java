package org.openjfx.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openjfx.model.Address;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class Student {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private LocalDate birthday;

    @Getter @Setter
    private int age;

    @Getter @Setter
    private String naturality;

    @Getter @Setter
    private String fatherName;

    @Getter @Setter
    private String motherName;

    @Getter @Setter
    private Address address;

    @Getter @Setter
    private String phone;

    @Getter @Setter
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
