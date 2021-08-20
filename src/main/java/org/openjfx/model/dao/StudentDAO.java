package org.openjfx.model.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openjfx.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Component
public class StudentDAO {

    private DbConnection dbConnection = new DbConnection();
    private Connection connection;

    public StudentDAO(){
        this.connection = dbConnection.getConnection();
    }

    /*public void registerStudent(Student student){
        String query = "INSERT INTO STUDENT " +
                "(ST_NAME, BIRTHDAY, AGE, NATURALITY, FATHER_NAME, MOTHER_NAME, PHONE, MESSAGE_PHONE)" +
                "VALUES" +
                "("+student.toSqlString()+");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }*/

    public ObservableList<Student> getAllStudents(){
        ObservableList <Student> studantes = FXCollections.observableArrayList();

        String query = "SELECT BIRTHDAY, AGE, NATURALITY, FATHER_NAME, MOTHER_NAME, PHONE, MESSAGE_PHONE, ST_NAME " +
                "FROM STUDENT;";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Student s = Student.builder().name(rs.getString("ST_NAME"))
                .birthday(rs.getDate("BIRTHDAY").toLocalDate())
                .age(rs.getInt("AGE"))
                .naturality(rs.getString("NATURALITY"))
                .fatherName(rs.getString("FATHER_NAME"))
                .motherName(rs.getString("MOTHER_NAME"))
                .phone(rs.getString("PHONE"))
                .messagePhone(rs.getString("MESSAGE_PHONE"))
                .build();
                studantes.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return studantes;
    }
}
