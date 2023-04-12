package com.lugiago.Model;

import java.time.LocalDateTime;
import java.util.Date;

public class Turno {
    
    private int id;
    private int idFuncionario;
    private String NomeFuncionario;
    private String Cargo;
    private LocalDateTime DataInicial;
    private LocalDateTime DataFinal;

    public Turno(int id, int idFuncionario, String NomeFuncionario, String Cargo, LocalDateTime DataInicial, LocalDateTime DataFinal) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.NomeFuncionario = NomeFuncionario;
        this.Cargo = Cargo;
        this.DataInicial = DataInicial;
        this.DataFinal = DataFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return NomeFuncionario;
    }

    public void setNomeFuncionario(String NomeFuncionario) {
        this.NomeFuncionario = NomeFuncionario;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public LocalDateTime getDataInicial() {
        return DataInicial;
    }

    public void setDataInicial(LocalDateTime DataInicial) {
        this.DataInicial = DataInicial;
    }

    public LocalDateTime getDataFinal() {
        return DataFinal;
    }

    public void setDataFinal(LocalDateTime DataFinal) {
        this.DataFinal = DataFinal;
    }
   
}