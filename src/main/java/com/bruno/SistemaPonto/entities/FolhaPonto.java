package com.bruno.SistemaPonto.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_pontos")
public class FolhaPonto {

    @Id
    private Long id;
    private LocalDate data;
    private LocalTime entradaManha;
    private LocalTime saidaManha;
    private LocalTime entradaTarde;
    private LocalTime saidaTarde;

    public FolhaPonto() {
    }

    public FolhaPonto(Long id, LocalDate data, LocalTime entradaManha, LocalTime saidaManha, LocalTime entradaTarde, LocalTime saidaTarde) {
        this.id = id;
        this.data = data;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getEntradaManha() {
        return entradaManha;
    }

    public void setEntradaManha(LocalTime entradaManha) {
        this.entradaManha = entradaManha;
    }

    public LocalTime getSaidaManha() {
        return saidaManha;
    }

    public void setSaidaManha(LocalTime saidaManha) {
        this.saidaManha = saidaManha;
    }

    public LocalTime getEntradaTarde() {
        return entradaTarde;
    }

    public void setEntradaTarde(LocalTime entradaTarde) {
        this.entradaTarde = entradaTarde;
    }

    public LocalTime getSaidaTarde() {
        return saidaTarde;
    }

    public void setSaidaTarde(LocalTime saidaTarde) {
        this.saidaTarde = saidaTarde;
    }
}
