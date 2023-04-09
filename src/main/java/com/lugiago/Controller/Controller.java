package com.lugiago.Controller;

import com.lugiago.db.Database;

import java.sql.*;

public abstract class Controller {
    /**
     * Pega informações do banco de dados
     * @param data
     * @return
     */
    public abstract ResultSet getData(String[] data) throws SQLException, ClassNotFoundException;

    /**
     * Atualiza informações no banco de dados
     * @param sql
     * @param newData
     */
    public abstract void updateData(String sql, Object[] newData) throws SQLException, ClassNotFoundException;

    /**
     * Insere novas informações no banco
     * @param sql
     */
    public abstract void insertData(String sql) throws SQLException, ClassNotFoundException;


}
