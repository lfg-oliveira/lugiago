package com.lugiago.View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class State {
    StateManager stateManager = null;
    // Superclasse responsável por gerenciar elementos e estado da janela
    protected static JFrame frame = new JFrame();
    public abstract void handleInput(String input);
    public abstract void draw();

    public State(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void addObject(List<Object> o, Object i) {
        o.add(i);
    }

    protected static void show(JPanel panel) {
        State.frame.getContentPane().removeAll();
        State.frame.getContentPane().add(panel, BorderLayout.CENTER);
        State.frame.revalidate();
        State.frame.repaint();
    }

    public static JFrame getFrame() {
        return State.frame;
    }
}
