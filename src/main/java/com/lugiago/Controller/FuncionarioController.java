package com.lugiago.Controller;

import com.lugiago.Model.Funcionario;
import com.lugiago.Model.FuncionarioDAO;
import java.util.List;

public class FuncionarioController{

    private static FuncionarioDAO dao = new FuncionarioDAO();
    
    public static boolean grava (int IdCargo, String Nome, int Codigo){
        Funcionario f = new Funcionario(0, IdCargo, null, Nome, Codigo, null);        
        return dao.insere(f);
    }
    
    public static List<Funcionario> getFuncionarios(){
        return dao.getAllFuncionarios();
    }
}
