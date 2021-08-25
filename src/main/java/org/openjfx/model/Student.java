package org.openjfx.model;

import lombok.*;
import org.openjfx.model.entity.Habitation;
import org.openjfx.model.entity.Health;
import org.openjfx.model.entity.SocialAssistance;

import java.time.LocalDate;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;
    private LocalDate birthday;
    private int age;
    private String naturality;
    private String fatherName;
    private boolean godfather;
    private boolean deadFather;
    private String motherName;
    private boolean godmother;
    private boolean deadMother;
    private String phone;
    private String messagePhone;
    private Address address;
    private Habitation habitation;
    private Health health;
    private SocialAssistance socialAssistance;

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
