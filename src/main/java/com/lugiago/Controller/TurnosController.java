package com.lugiago.Controller;

import com.lugiago.Model.Turno;
import com.lugiago.Model.TurnoDAO;
import java.util.Date;
import java.util.List;

public class TurnosController{

    private static TurnoDAO dao = new TurnoDAO();;
    
    public static void grava (int id, int idFuncionario, String NomeFuncionario, String Cargo, Date DataInicial, Date DataFinal) {
        Turno t = new Turno(id, idFuncionario, NomeFuncionario, Cargo, DataInicial, DataFinal);
        dao.insere(t);
    }
    
    public static List<Turno> getFuncionarios(){
        return dao.getAllTurnos();
    }
}
