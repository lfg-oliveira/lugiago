package com.lugiago.Model;

import com.lugiago.Utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDAO {

    //Função responsavel por inserir os funcionarios no banco de dados.
    public boolean insere(Funcionario funcionario) {
        PreparedStatement comando = null;
        Connection con = null;

        try {
           
            con = Database.getConnection();
            String sql = "insert into Funcionario(Nome,IdCargo,Codigo) values (?,?,?)";
            comando = con.prepareStatement(sql);
            comando.setString(1, funcionario.getNome());
            comando.setInt(2, funcionario.getIdCargo());
            comando.setString(3, funcionario.getCodigo());
            comando.executeUpdate();

            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                comando.close();
                con.close();
            } catch (NullPointerException | SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }
    
    /***
     * Função responsável por retornar do banco de dados todos os Funcionários cadastrados.
     * @return Uma Lista de Funcionários
     */
    public List<Funcionario> getAllFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        Statement comando = null;
        Connection con = null;
        
        try {
            con = Database.getConnection();

            // Comando SQL responsavel por obter os funcionarios no banco de dados.
            comando = con.createStatement();
            String sql = "select f.Id, f.Nome, f.IdCargo, c.Descricao as Cargo, f.Codigo,c.TipoCodigo"
                    + " from Funcionario f"
                    + " join Cargo c on c.id = f.IdCargo"
                    + " order by f.id";
            ResultSet result = comando.executeQuery(sql);

            // Enquanto houver dados no result.
            while (result.next()) {
                // Ler o dado.
                int id = result.getInt("id");
                String nome = result.getString("nome");
                int idCargo = result.getInt("idCargo");
                String cargo = result.getString("Cargo");
                String codigo = result.getString("codigo");
                String tipoCodigo = result.getString("tipoCodigo");

                funcionarios.add(new Funcionario(id, idCargo, cargo, nome, codigo, tipoCodigo));
            }

            return funcionarios;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                comando.close();
                con.close();
            } catch (NullPointerException | SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return funcionarios;
    }
}
