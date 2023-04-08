package com.lugiago.View;

import com.lugiago.Controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuState extends State{
    private List<JButton> buttonList;
    private List<String>  controllerAction = new ArrayList<>();
    /**
     * Método para processar os eventos de clique dos botões
     * @param input
     */
    @Override
    public void handleInput() {

    }

    /**
     * Desenha os elementos do estado atual na tela
     * @param frame
     */
    @Override
    public void draw() {
        // Configurando o layout do painel
        buttonList = new ArrayList<>();

        JPanel panel = new JPanel(new GridLayout(buttonList.size(), 1, 10, 20));
        panel.setBorder(new EmptyBorder(200, 100, 100, 100));
        // Criando botões para cada ação
        JButton cadastrarFuncionarioButton = new JButton("Cadastro de funcionário");
        JButton planejamentoTurnosButton = new JButton("Planejamento de Turnos");
        JButton trocaPlantoesButton = new JButton("Troca de plantões");
        JButton imprimirTurnosButton = new JButton("Impressão dos turnos no mês");

        // Nome das função que será executada por cada
        controllerAction.add("cadastrar");
        controllerAction.add("planejar");
        controllerAction.add("trocar");
        controllerAction.add("imprimir");

        // Adicionando botões à lista
        buttonList.add(cadastrarFuncionarioButton);
        buttonList.add(planejamentoTurnosButton);
        buttonList.add(trocaPlantoesButton);
        buttonList.add(imprimirTurnosButton);

        // Adicionando botões ao painel
        for (JButton button : buttonList) {
            panel.add(button);
        }

        // Adicionando o painel ao quadro
        State.show(panel);
    }

    public MainMenuState(StateManager stateManager) {
        super(stateManager);
    }

    public MainMenuState(StateManager stateManager, Controller controller) {
        super(stateManager, controller);
    }
}
