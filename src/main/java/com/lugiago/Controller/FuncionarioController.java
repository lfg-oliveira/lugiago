package com.lugiago.Controller;

import com.lugiago.Model.Funcionario;
import com.lugiago.Model.FuncionarioDAO;
import java.util.List;

public class FuncionarioController{

    private static FuncionarioDAO dao = new FuncionarioDAO();
    
    public static void grava (int Id, int IdCargo, String Cargo, String Nome, int Codigo, String TipoCodigo){
        Funcionario f = new Funcionario(Id, IdCargo, Cargo, Nome, Codigo, TipoCodigo);        
        dao.insere(f);
    }
    
    public static List<Funcionario> getFuncionarios(){
        return dao.getAllFuncionarios();
    }
}
