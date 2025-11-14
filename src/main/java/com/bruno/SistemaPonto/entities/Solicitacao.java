package com.bruno.SistemaPonto.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
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
}
