package com.bruno.SistemaPonto.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_solicitacoes")
public class Solicitacao {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ponto_id", nullable = false)
    private FolhaPonto ponto;

    private String diaPontoOriginal;
    private String entradaManhaOriginal;
    private String saidaManhaOriginal;
    private String entradaTardeOriginal;
    private String saidaTardeOriginal;

    private String entradaManhaNovo;
    private String saidaManhaNovo;
    private String entradaTardeNovo;
    private String saidaTardeNovo;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;
    private String diaCriacao;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User userAdmin;

    public Solicitacao() {
    }

    public Solicitacao(User user, FolhaPonto ponto, String diaPontoOriginal, String entradaManhaOriginal, String saidaManhaOriginal, String entradaTardeOriginal, String saidaTardeOriginal, String entradaManhaNovo, String saidaManhaNovo, String entradaTardeNovo, String saidaTardeNovo, String motivo, String diaCriacao, User userAdmin) {
        this.user = user;
        this.ponto = ponto;
        this.diaPontoOriginal = diaPontoOriginal;
        this.entradaManhaOriginal = entradaManhaOriginal;
        this.saidaManhaOriginal = saidaManhaOriginal;
        this.entradaTardeOriginal = entradaTardeOriginal;
        this.saidaTardeOriginal = saidaTardeOriginal;
        this.entradaManhaNovo = entradaManhaNovo;
        this.saidaManhaNovo = saidaManhaNovo;
        this.entradaTardeNovo = entradaTardeNovo;
        this.saidaTardeNovo = saidaTardeNovo;
        this.motivo = motivo;
        this.status = StatusSolicitacao.PENDENTE;
        this.diaCriacao = diaCriacao;
        this.userAdmin = userAdmin;
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

    public FolhaPonto getPonto() {
        return ponto;
    }

    public void setPonto(FolhaPonto ponto) {
        this.ponto = ponto;
    }

    public String getDiaPontoOriginal() {
        return diaPontoOriginal;
    }

    public void setDiaPontoOriginal(String diaPontoOriginal) {
        this.diaPontoOriginal = diaPontoOriginal;
    }

    public String getEntradaManhaOriginal() {
        return entradaManhaOriginal;
    }

    public void setEntradaManhaOriginal(String entradaManhaOriginal) {
        this.entradaManhaOriginal = entradaManhaOriginal;
    }

    public String getSaidaManhaOriginal() {
        return saidaManhaOriginal;
    }

    public void setSaidaManhaOriginal(String saidaManhaOriginal) {
        this.saidaManhaOriginal = saidaManhaOriginal;
    }

    public String getEntradaTardeOriginal() {
        return entradaTardeOriginal;
    }

    public void setEntradaTardeOriginal(String entradaTardeOriginal) {
        this.entradaTardeOriginal = entradaTardeOriginal;
    }

    public String getSaidaTardeOriginal() {
        return saidaTardeOriginal;
    }

    public void setSaidaTardeOriginal(String saidaTardeOriginal) {
        this.saidaTardeOriginal = saidaTardeOriginal;
    }

    public String getEntradaManhaNovo() {
        return entradaManhaNovo;
    }

    public void setEntradaManhaNovo(String entradaManhaNovo) {
        this.entradaManhaNovo = entradaManhaNovo;
    }

    public String getSaidaManhaNovo() {
        return saidaManhaNovo;
    }

    public void setSaidaManhaNovo(String saidaManhaNovo) {
        this.saidaManhaNovo = saidaManhaNovo;
    }

    public String getEntradaTardeNovo() {
        return entradaTardeNovo;
    }

    public void setEntradaTardeNovo(String entradaTardeNovo) {
        this.entradaTardeNovo = entradaTardeNovo;
    }

    public String getSaidaTardeNovo() {
        return saidaTardeNovo;
    }

    public void setSaidaTardeNovo(String saidaTardeNovo) {
        this.saidaTardeNovo = saidaTardeNovo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public String getDiaCriacao() {
        return diaCriacao;
    }

    public void setDiaCriacao(String diaCriacao) {
        this.diaCriacao = diaCriacao;
    }

    public User getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(User userAdmin) {
        this.userAdmin = userAdmin;
    }
}
