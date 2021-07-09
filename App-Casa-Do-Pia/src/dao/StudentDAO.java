package dao;

import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDAO {

    private DbConnection dbConnection = new DbConnection();
    private Connection connection;

    public StudentDAO(){
        this.connection = dbConnection.getConnection();
    }

    public void registerStudent(Student student){
        System.out.println(student.toSqlString());
        String query = "INSERT INTO STUDENT " +
                "(NAME, BIRTHDAY, AGE, NATURALITY, FATHER_NAME, MOTHER_NAME, PHONE, MESSAGE_PHONE)" +
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
}
