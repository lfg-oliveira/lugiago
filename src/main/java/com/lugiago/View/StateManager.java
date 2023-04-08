package com.lugiago.View;

import javax.swing.*;
import java.util.Stack;

public class StateManager {
    private State currentState;
    private Stack<State> previousStateStack;
    public StateManager() {
        this.currentState = new MainMenuState(this);

        JFrame frame = State.getFrame();
        getCurrentState().draw();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public StateManager(State currentState) {
        this.currentState = currentState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        if(this.currentState != null) {
            this.previousStateStack.push(this.currentState);
        }
        this.currentState = currentState;
    }

    public State getPreviousState() {
        return this.previousStateStack.pop();
    }
}
