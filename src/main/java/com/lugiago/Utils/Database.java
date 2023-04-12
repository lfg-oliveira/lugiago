package com.lugiago.Utils;

import java.sql.*;

/**
 * Classe responsavel por conectar com o banco de dados
 */
public class Database {
    private static Connection connection;
    
    private final static String DBURL = "jdbc:mysql://localhost:3306/hospital?allowPublicKeyRetrieval=true";
    private final static String USER = "lugiago";
    private final static String PASSWORD = "1234";
    
    private Database() {}

    /**
     * Obtem uma nova conexão com o banco de dados.
     * @return uma conexão válida.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(Database.connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
        }else if(Database.connection.isClosed()){
            connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
        }
        return connection;
    }
}
