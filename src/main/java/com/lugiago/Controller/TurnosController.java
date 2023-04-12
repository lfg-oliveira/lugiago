package com.lugiago.Controller;

import com.lugiago.Model.Turno;
import com.lugiago.Model.TurnoDAO;
import java.time.LocalDateTime;
import java.util.List;

public class TurnosController {

    private static TurnoDAO dao = new TurnoDAO();

    /**
     * Insere um novo Turno (Não realiza verificações, apenas insere).
     *
     * @param idFuncionario Id do Funcionário plantonista
     * @param DataInicial Início do Plantão
     */
    public static void grava(int idFuncionario, LocalDateTime DataInicial) {
        Turno t = new Turno(0, idFuncionario, null, null, DataInicial, null);

        dao.insere(t);
    }

    /**
     * Altera um Turno específicado (Não realiza verificações, apenas altera).
     *
     * @param idFuncionario Id do Funcionário plantonista
     * @param DataInicial Início do Plantão
     * @return boolean indicando sucesso ou não da operação
     */
    public static boolean alterar(int idTurno, int idFuncionario, LocalDateTime DataInicial) {

        Turno t = new Turno(idTurno, idFuncionario, null, null, DataInicial, null);

        return dao.altera(t);
    }

    /**
     * Função responsável por retornar todos os Turnos cadastrados.
     *
     * @return Lista de Turnos
     */
    public static List<Turno> getTurnos() {
        return dao.getAllTurnos();
    }

    /**
     * Verifica se um plantonista pode realizar o ínicio de um plantão no
     * horário fornecido sem ferir a regra 12/36
     *
     * @param idFuncionario Id do funcionário a ser verificada a disponibilidade
     * @param DataInicial Inicio do Turno a ser verificada a disponibilidade
     * @param turnoId Id de um Turno a ser ignorado na verificação (Em casos de
     * alteração e não inserção)
     * @return boolean indicando se o periodo está válido para o plantonista
     */
    public static boolean validaDisponibiliade(int idFuncionario, LocalDateTime DataInicial, int turnoId) {
        Turno t = new Turno(turnoId, idFuncionario, null, null, DataInicial, null);

        return dao.valida(t);
    }

    /**
     * Verifica se um plantonista pode realizar o ínicio de um plantão no
     * horário fornecido sem ferir a regra 12/36
     *
     * @param idFuncionario Id do funcionário a ser verificada a disponibilidade
     * @param DataInicial Inicio do Turno a ser verificada a disponibilidade
     * @return boolean indicando se o periodo está válido para o plantonista
     */
    public static boolean validaDisponibiliade(int idFuncionario, LocalDateTime DataInicial) {
        Turno t = new Turno(0, idFuncionario, null, null, DataInicial, null);

        return dao.valida(t);
    }
}
