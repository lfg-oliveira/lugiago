package com.lugiago.Model;

import com.lugiago.db.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Model {
    Connection con = Database.getConnection();

    protected Model() throws SQLException, ClassNotFoundException {
    }

    /**
     * Responsável por pegar informações no BD
     * @param sql
     * @return
     */
    public abstract ResultSet getData(String sql);

    /**
     * Insere novos dados no banco de dados
     * @param sql
     */
    public abstract void insertData(String sql);

    /**
     * Atualiza os dados existentes no BD
     * @param sql
     */
    public abstract void updateData(String sql);
}
