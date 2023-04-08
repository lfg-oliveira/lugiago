package com.lugiago.Database;

import java.sql.*;

/**
 * Classe responsavel por conectar com o banco de dados
 */
public class Database {
    private static Connection connection;
    private Database() {}

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(Database.connection == null) {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital", "lugiago", "1234");
        }
        return connection;
    }
}
