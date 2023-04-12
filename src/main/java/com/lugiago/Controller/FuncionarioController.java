package com.lugiago.Controller;

import com.lugiago.Model.Funcionario;
import com.lugiago.Model.FuncionarioDAO;
import java.util.List;

public class FuncionarioController {

    private static FuncionarioDAO dao = new FuncionarioDAO();

    /**
     * Grava um novo Funcionário
     *
     * @param IdCargo Id do Cargo do Funcionário
     * @param Nome Nome do Funcionário
     * @param Codigo Código de documento de registro desse funcionário
     * @return boolean indicando sucesso ou não dessa operação
     */
    public static boolean grava(int IdCargo, String Nome, String Codigo) {
        Funcionario f = new Funcionario(0, IdCargo, null, Nome, Codigo, null);
        return dao.insere(f);
    }

    /**
     * Função responsável por retornar todos os Funcionários cadastrados.
     *
     * @return Uma Lista de Funcionários
     */
    public static List<Funcionario> getFuncionarios() {
        return dao.getAllFuncionarios();
    }
}
