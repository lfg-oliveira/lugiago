package com.lugiago.Model;

import com.lugiago.Utils.Database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gioma
 */
public class TurnoDAO {

    //Função responsavel por popular a lista de Turnos na DAO.
    public TurnoDAO() {
    }

    //Função para inserior turno no banco de dados.
    public void insere(Turno turno) {
        PreparedStatement comando = null;
        Connection con = null;

        try {

            con = Database.getConnection();
            String sql = "insert into Turno(idFuncionario,data) values (?,?)";
            comando = con.prepareStatement(sql);
            comando.setInt(1, turno.getIdFuncionario());
            comando.setTimestamp(2, Timestamp.valueOf(turno.getDataInicial()));
            comando.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                comando.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Função para validar se o turno em questão está valido de acordo com a regra 12/36
    //A query retornara a quantidade de turnos do mesmo funcionario cadastrados dentro do periodo reservado
    //Caso seja 1 ou maior , o turno não pode ser cadastrado e a função retornará falso.
    public boolean valida(Turno turno) {
        PreparedStatement comando = null;
        Connection con = null;

        try {

            con = Database.getConnection();
            String sql = "select count(id) as quantity"
                    + " from Turno t"
                    + " where idFuncionario = ?"
                    + " and Data > DATE_ADD(?, interval -48 HOUR) and Data < DATE_ADD(?, interval +48 HOUR)";
            if (turno.getId() > 0) {
                sql += "and t.id <> ?";
            }
            
            comando = con.prepareStatement(sql);

            comando.setInt(1, turno.getIdFuncionario());
            comando.setTimestamp(2, Timestamp.valueOf(turno.getDataInicial()));
            comando.setTimestamp(3, Timestamp.valueOf(turno.getDataInicial()));
            if (turno.getId() > 0) {
                comando.setInt(4, turno.getId());    
            }
            ResultSet result = comando.executeQuery();

            while (result.next()) {
                // Ler o resultado.
                int resultado = result.getInt("quantity");

                if (resultado > 0) {
                    return false;
                }
            }

            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                comando.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public List<Turno> getAllTurnos() {
        List<Turno> turnos = new ArrayList<>();
        Statement comando = null;
        Connection con = null;

        try {

            con = Database.getConnection();

            // Comando SQL responsavel por obter os Turnos no banco de dados.
            comando = con.createStatement();
            String sql = "select t.id, t.IdFuncionario, f.nome as NomeFuncionario, c.descricao as Cargo, t.data as DataInicial, DATE_ADD(t.data,interval 12 hour) as DataFinal"
                    + " from Turno t"
                    + " join Funcionario f on t.IdFuncionario = f.Id"
                    + " join Cargo c on c.Id = f.IdCargo";

            ResultSet result = comando.executeQuery(sql);

            // Enquanto houver dados no result.
            while (result.next()) {
                // Ler o dado.
                int id = result.getInt("id");
                int idFuncionario = result.getInt("idFuncionario");
                String nome = result.getString("NomeFuncionario");
                String cargo = result.getString("Cargo");
                LocalDateTime dataInicial = result.getTimestamp("DataInicial").toLocalDateTime();
                LocalDateTime dataFinal = result.getTimestamp("DataFinal").toLocalDateTime();

                turnos.add(new Turno(id, idFuncionario, nome, cargo, dataInicial, dataFinal));
            }

            return turnos;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                comando.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return turnos;
    }
}
