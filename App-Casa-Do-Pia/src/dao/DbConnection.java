package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private Connection connection;

    public DbConnection(){
        String dbUser = "gmacias";
        String dbPwd = "admin";
        String dbName = "casa_do_pia";
        String url = "jdbc:mysql://localhost/"+dbName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, dbUser, dbPwd);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();git st
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
