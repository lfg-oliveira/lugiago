package com.lugiago.View;

import java.util.HashMap;

public class Menu {
    final HashMap<String, String> menuMap = new HashMap<>();
    String state = "main";
    public Menu() {
        menuMap.put("main", "Menu Principal:\n"+
                "\n1 - Cadastrar");
    }
}