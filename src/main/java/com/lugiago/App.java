package com.lugiago;

import com.lugiago.Database.Database;
import com.lugiago.View.StateManager;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Connection con;
        try {
            con = Database.getConnection();
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar ao banco.\n"+e);
        } catch (ClassNotFoundException c) {
            System.out.println("Driver do Banco de dados não funciona.\n"+c);
        }
        StateManager st = new StateManager();
    }
}
