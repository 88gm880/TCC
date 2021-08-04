package org.openjfx.model;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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
