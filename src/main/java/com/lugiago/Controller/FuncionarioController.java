package com.lugiago.Controller;

import com.lugiago.Model.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioController extends Controller{
    /**
     * Checa se a regra 12/36 está sendo cumprida
     *
     * @param data deve conter, respectivamente, idFuncionario, dataAtual
     * @return Retorna ResultSet com uma coluna quantity. Se quantity > 0, a regra 36/12 não foi respeitada, então a
     * ação deve ser desfeita
     */
    @Override
    public ResultSet getData(String[] data) throws SQLException, ClassNotFoundException {
        String query = "select count(id) as quantity\n" +
                "from Turno t \n" +
                "where idFuncionario = ?\n" +
                "and Data > DATE_ADD('?', interval -36 HOUR) and Data < DATE_ADD('?', interval +48 HOUR)";
        return new Funcionario().getData(query, data);
    }

    /**
     * Atualiza informações no banco de dados
     *
     * @param sql
     * @param newData
     */
    @Override
    public void updateData(String sql, Object[] newData) throws SQLException, ClassNotFoundException {
        new Funcionario().updateData(sql, newData);
    }

    /**
     * Insere novas informações no banco
     * <code>INSERT INTO funcionario VALUES ()</code>
     * @param sql
     */
    @Override
    public void insertData(String sql) throws SQLException, ClassNotFoundException {
        new Funcionario().insertData(sql);
    }
}
