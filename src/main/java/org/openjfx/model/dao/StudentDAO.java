package org.openjfx.model.dao;

import org.openjfx.model.Student;
import org.openjfx.model.StudentImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private DbConnection dbConnection = new DbConnection();
    private Connection connection;

    public StudentDAO(){
        this.connection = dbConnection.getConnection();
    }

    public void registerStudent(Student student){
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
    }

    public List<Student> getAllStudents(){
        ArrayList <Student> studantes = new ArrayList<>();

        String query = "SELECT BIRTHDAY, AGE, NATURALITY, FATHER_NAME, MOTHER_NAME, PHONE, MESSAGE_PHONE, ST_NAME " +
                "FROM STUDENT;";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Student s = new StudentImpl();
                s.setName(rs.getString("ST_NAME"));
                s.setBirthday(rs.getDate("BIRTHDAY").toLocalDate());
                s.setAge(rs.getInt("AGE"));
                s.setNaturality(rs.getString("NATURALITY"));
                s.setFatherName(rs.getString("FATHER_NAME"));
                s.setMotherName(rs.getString("MOTHER_NAME"));
                s.setPhone(rs.getString("PHONE"));
                s.setMessagePhone(rs.getString("MESSAGE_PHONE"));
                studantes.add(s);
                System.out.println(s.toSqlString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return studantes;
    }
}
