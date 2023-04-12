package com.lugiago.Controller;

import com.lugiago.Model.Turno;
import com.lugiago.Model.TurnoDAO;
import java.time.LocalDateTime;
import java.util.List;

public class TurnosController{

    private static TurnoDAO dao = new TurnoDAO();;
    
    public static void grava (int idFuncionario, LocalDateTime DataInicial) {
        Turno t = new Turno(0, idFuncionario, null, null, DataInicial, null);
        
        dao.insere(t);
    }
    
    public static List<Turno> getTurnos(){
        return dao.getAllTurnos();
    }
    
    public static boolean validaDisponibiliade(int idFuncionario, LocalDateTime DataInicial, int turnoId){
        Turno t = new Turno(turnoId, idFuncionario, null, null, DataInicial, null);
        
        return dao.valida(t);
    }
    
    public static boolean validaDisponibiliade(int idFuncionario, LocalDateTime DataInicial ){
        Turno t = new Turno(0, idFuncionario, null, null, DataInicial, null);
        
        return dao.valida(t);
    }
}
