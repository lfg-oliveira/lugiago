package com.lugiago.Model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author gioma
 */
public class Turno {
    
    private int id;
    private int idFuncionario;
    private String NomeFuncionario;
    private String Cargo;
    private Date DataInicial;
    private Date DataFinal;

    public Turno(int id, int idFuncionario, String NomeFuncionario, String Cargo, Date DataInicial, Date DataFinal) {
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

    public Date getDataInicial() {
        return DataInicial;
    }

    public void setDataInicial(Date DataInicial) {
        this.DataInicial = DataInicial;
    }

    public Date getDataFinal() {
        return DataFinal;
    }

    public void setDataFinal(Date DataFinal) {
        this.DataFinal = DataFinal;
    }
   
}