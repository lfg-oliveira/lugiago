package com.lugiago.Model;

import com.lugiago.Utils.Database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gioma
 */
public class TurnoDAO {
    private List<Turno> turnos = null;
    
    //Função responsavel por popular a lista de Turnos na DAO.
    
    public TurnoDAO(){
        Statement comando = null;
        Connection con = null;
        
         try {
            if (turnos == null) {
                turnos = new ArrayList<>();

                con = Database.getConnection();

                // Comando SQL responsavel por obter os Turnos no banco de dados.
                comando = con.createStatement();
                String sql = "select t.id,t.IdFuncionario,f.nome as NomeFuncionario,c.descricao as Cargo,t.data as DataInicial,DATE_ADD(t.data,interval 12 hour) as DataFinal\n" +
                             "from turno t\n" +
                             "join Funcionario f on t.IdFuncionario = f.Id \n" +
                             "join Cargo c on c.Id = f.Cargo ";

                ResultSet result = comando.executeQuery(sql);

                // Enquanto houver dados no result.
                while (result.next()) {
                    // Ler o dado.
                    int id = result.getInt("id");
                    int idFuncionario = result.getInt("idFuncionario");
                    String nome = result.getString("NomeFuncionario");
                    String cargo = result.getString("Cargo");
                    Date dataInicial = result.getDate("DataInicial");
                    Date dataFinal = result.getDate("DataFinal");
                    
                    turnos.add(new Turno(id,idFuncionario,nome,cargo,dataInicial,dataFinal));
                }
            }
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
    
    //Função para inserior turno no banco de dados.
    
    public void insere(Turno turno){
        PreparedStatement comando = null;
        Connection con = null;
        
         try {
           
            con = Database.getConnection();            
            String sql = "insert into Turnos(idFuncionario,data) values (?,?)";
            comando = con.prepareStatement(sql);
            comando.setInt(1, turno.getIdFuncionario());
            comando.setDate(2, new java.sql.Date(turno.getDataInicial().getTime()));
            comando.executeUpdate();

            turnos.add(turno);
            
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
    public boolean valida(Turno turno){
      PreparedStatement comando = null;
        Connection con = null;
        
         try {
           
            con = Database.getConnection();
            comando = (PreparedStatement) con.createStatement();
            String sql = "select count(id) as quantity\n" +
                         "from Turno t \n" +
                         "where idFuncionario = ?\n" +
                         "and Data > DATE_ADD(?, interval -48 HOUR) and Data < DATE_ADD(?, interval +48 HOUR)";
            comando.setInt(1, turno.getIdFuncionario());
            comando.setDate(2, new java.sql.Date(turno.getDataInicial().getTime()));
            comando.setDate(3, new java.sql.Date(turno.getDataInicial().getTime()));
            ResultSet result = comando.executeQuery(sql);


                while (result.next()) {
                    // Ler o resultado.
                  int resultado = result.getInt("quantity");
                  
                  if (resultado > 0)
                    return false;
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
        return turnos;
   }
}