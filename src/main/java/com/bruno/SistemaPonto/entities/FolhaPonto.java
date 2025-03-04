package com.bruno.SistemaPonto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pontos")
public class FolhaPonto {

    @Id
    private Long id;
    private String dia;
    private String entradaManha;
    private String saidaManha;
    private String entradaTarde;
    private String saidaTarde;

    public FolhaPonto() {
    }

    public FolhaPonto(Long id, String dia, String entradaManha, String saidaManha, String entradaTarde, String saidaTarde) {
        this.id = id;
        this.dia = dia;
        this.entradaManha = entradaManha;
        this.saidaManha = saidaManha;
        this.entradaTarde = entradaTarde;
        this.saidaTarde = saidaTarde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getEntradaManha() {
        return entradaManha;
    }

    public void setEntradaManha(String entradaManha) {
        this.entradaManha = entradaManha;
    }

    public String getSaidaManha() {
        return saidaManha;
    }

    public void setSaidaManha(String saidaManha) {
        this.saidaManha = saidaManha;
    }

    public String getEntradaTarde() {
        return entradaTarde;
    }

    public void setEntradaTarde(String entradaTarde) {
        this.entradaTarde = entradaTarde;
    }

    public String getSaidaTarde() {
        return saidaTarde;
    }

    public void setSaidaTarde(String saidaTarde) {
        this.saidaTarde = saidaTarde;
    }
}
