package com.bruno.SistemaPonto.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_pontos")
public class FolhaPonto {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String dia;
    private String entradaManha;
    private String saidaManha;
    private String entradaTarde;
    private String saidaTarde;
    private String totalHoras;

    public FolhaPonto() {
    }

    public FolhaPonto(UUID id, String dia, String entradaManha, String saidaManha, String entradaTarde, String saidaTarde) {
        this.id = id;
        this.dia = dia;
        this.entradaManha = entradaManha;
        this.saidaManha = saidaManha;
        this.entradaTarde = entradaTarde;
        this.saidaTarde = saidaTarde;
        this.totalHoras = "--:--";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getTotalHoras() {
        return totalHoras;
    }

    public void atualizarTotalHoras() {
        this.totalHoras = calcularTotalHoras();
    }

    public String calcularTotalHoras() {
        try {
            if (entradaManha == null || saidaManha == null || entradaTarde == null || saidaTarde == null) return "--:--";

            int minutosTrabalhados = calcularDiferencaMinutos(entradaManha, saidaManha) + calcularDiferencaMinutos(entradaTarde, saidaTarde);
            int horas = minutosTrabalhados / 60;
            int minutos = minutosTrabalhados % 60;

            return String.format("%02d:%02d", horas, minutos);
        } catch (Exception e) {
            return "--:--";
        }
    }

    private int calcularDiferencaMinutos(String inicio, String fim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horaInicio = LocalTime.parse(inicio, formatter);
        LocalTime horaFim = LocalTime.parse(fim, formatter);
        return (int) Duration.between(horaInicio, horaFim).toMinutes();
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
