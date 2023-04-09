
package com.lugiago.Model;

/**
 *
 * @author gioma
 */
public class Funcionario {
    
    private int Id;
    private int IdCargo;
    private String Cargo;
    private String Nome;
    private int Codigo;
    private String TipoCodigo;

    public Funcionario(int Id, int IdCargo, String Cargo, String Nome, int Codigo, String TipoCodigo) {
        this.Id = Id;
        this.IdCargo = IdCargo;
        this.Cargo = Cargo;
        this.Nome = Nome;
        this.Codigo = Codigo;
        this.TipoCodigo = TipoCodigo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(int IdCargo) {
        this.IdCargo = IdCargo;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getTipoCodigo() {
        return TipoCodigo;
    }

    public void setTipoCodigo(String TipoCodigo) {
        this.TipoCodigo = TipoCodigo;
    }
    
}