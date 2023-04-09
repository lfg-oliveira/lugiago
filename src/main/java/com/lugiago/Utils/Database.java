package com.lugiago.Utils;

import java.sql.*;

/**
 * Classe responsavel por conectar com o banco de dados
 */
public class Database {
    private static Connection connection;
    private Database() {}

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(Database.connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "lugiago", "1234");
        }
        return connection;
    }
}
