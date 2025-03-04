package com.bruno.SistemaPonto.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_pontos")
public class FolhaPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolhaPonto that = (FolhaPonto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
