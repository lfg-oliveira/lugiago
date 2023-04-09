package com.lugiago.Controller;

import com.lugiago.db.Database;

import java.sql.*;

public abstract class Controller {
    protected Controller() throws SQLException, ClassNotFoundException {
    }

    /**
     * Pega informações do banco de dados
     * @param sql
     * @return
     */
    public abstract ResultSet getData(String sql);

    /**
     * Atualiza informações no banco de dados
     * @param sql
     * @param newData
     */
    public abstract void updateData(String sql, String[] newData);

    /**
     * Insere novas informações no banco
     * @param sql
     */
    public abstract void insertData(String sql);


}
