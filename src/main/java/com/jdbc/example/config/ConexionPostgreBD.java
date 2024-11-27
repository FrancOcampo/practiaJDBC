package com.jdbc.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPostgreBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/jdbc_practice";
    private static final String USER = "postgres";
    private static final String PASSWORD = "43235085";

    public static Connection getConnection() throws SQLException {
        /*try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            throw new RuntimeException("error al cargar driver PostgreSQL",e);
        }*/

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
