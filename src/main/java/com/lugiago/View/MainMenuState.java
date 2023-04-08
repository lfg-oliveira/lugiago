package com.lugiago.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuState extends State{
    /**
     * Método para processar os eventos de clique dos botões
     * @param input
     */
    private List<JButton> buttonList;
    @Override
    public void handleInput(String input) {

    }

    /**
     * Desenha os elementos do estado atual na tela
     * @param frame
     */
    @Override
    public void draw() {
        // Configurando o layout do painel
        JPanel panel = new JPanel(new GridLayout(buttonList.size(), 1));
        panel.setBorder(new EmptyBorder(200, 100, 100, 100));
        // Adicionando botões ao painel
        for (JButton button : buttonList) {
            panel.add(button);
        }

        // Adicionando o painel ao quadro
        State.show(panel);
    }

    public MainMenuState(StateManager stateManager) {
        super(stateManager);
        buttonList = new ArrayList<>();

        // Criando botões para cada ação
        JButton cadastrarFuncionarioButton = new JButton("Cadastro de funcionário");
        JButton planejamentoTurnosButton = new JButton("Planejamento de Turnos");
        JButton trocaPlantoesButton = new JButton("Troca de plantões");
        JButton imprimirTurnosButton = new JButton("Impressão dos turnos no mês");

        // Adicionando botões à lista
        buttonList.add(cadastrarFuncionarioButton);
        buttonList.add(planejamentoTurnosButton);
        buttonList.add(trocaPlantoesButton);
        buttonList.add(imprimirTurnosButton);
    }

}
