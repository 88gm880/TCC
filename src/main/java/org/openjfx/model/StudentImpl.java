package org.openjfx.model;

import java.time.LocalDate;

public class StudentImpl extends Student {

    public StudentImpl(){

    }

    public StudentImpl(String name,
                       LocalDate birthday,
                       int age,
                       String naturality,
                       String fatherName,
                       String motherName,
                       String phone,
                       String messagePhone) {

        this.setName(name);
        this.setBirthday(birthday);
        this.setAge(age);
        this.setNaturality(naturality);
        this.setFatherName(fatherName);
        this.setMotherName(motherName);
        this.setPhone(phone);
        this.setMessagePhone(messagePhone);
    }
}
