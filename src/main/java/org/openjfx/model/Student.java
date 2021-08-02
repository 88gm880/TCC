package org.openjfx.model;

import java.time.LocalDate;

public abstract class Student {

    private String name;

    private LocalDate birthday;

    private int age;

    private String naturality;

    private String fatherName;

    private String motherName;

    private Address address;

    private String phone;

    private String messagePhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNaturality() {
        return naturality;
    }

    public void setNaturality(String naturality) {
        this.naturality = naturality;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessagePhone() {
        return messagePhone;
    }

    public void setMessagePhone(String messagePhone) {
        this.messagePhone = messagePhone;
    }

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
