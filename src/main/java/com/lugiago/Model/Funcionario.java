package com.lugiago.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Funcionario extends Model{
    /**
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Funcionario() throws SQLException, ClassNotFoundException {
        super();
    }

    /**
     * Responsável por pegar informações no BD
     *
     * @param sql
     * @return
     */
    @Override
    public ResultSet getData(String sql, String[] information) throws SQLException {
        Statement statement = con.createStatement();
        return statement.executeQuery(sql);
    }

    /**
     * Insere novos dados no banco de dados
     *
     * @param sql
     */
    @Override
    public void insertData(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        if(stmt.executeUpdate(sql)> 0) {
            System.out.println("Query rodou com sucesso.");
        }
    }

    /**
     * Atualiza os dados existentes no BD
     *
     * @param sql
     * @param newData
     */
    @Override
    public void updateData(String sql, Object[] newData) throws SQLException {

    }
}
