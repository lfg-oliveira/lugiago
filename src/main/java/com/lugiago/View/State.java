package com.lugiago.View;


import com.lugiago.Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class State {
    StateManager stateManager = null;
    Controller controller;
    // Superclasse responsável por gerenciar elementos e estado da janela
    protected static final JFrame frame = new JFrame();
    public abstract void handleInput();
    public abstract void draw();

    public State(StateManager stateManager) {
        this.stateManager = stateManager;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public final void  addObject(List<Object> o, Object i) {
        o.add(i);
    }

    protected static void show(JPanel panel) {
        State.frame.getContentPane().removeAll();
        State.frame.getContentPane().add(panel, BorderLayout.CENTER);
        State.frame.revalidate();
        State.frame.repaint();
    }

    public State(StateManager stateManager, Controller controller){
        this.stateManager = stateManager;
        draw();
        setController(controller);
    }
    public final static JFrame getFrame() {
        return State.frame;
    }
}
