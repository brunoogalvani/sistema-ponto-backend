package com.bruno.SistemaPonto.dto;

import com.bruno.SistemaPonto.entities.Solicitacao;
import com.bruno.SistemaPonto.entities.StatusSolicitacao;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class SolicitacaoDTO {

    private UUID id;
    private UUID userId;
    private UUID pontoId;
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
    private StatusSolicitacao status;
    private String diaCriacao;
    private UUID userAdminId;

    public SolicitacaoDTO() {
    }

    public SolicitacaoDTO(Solicitacao entity) {
        BeanUtils.copyProperties(entity, this);
        this.userId = entity.getUser().getId();
        this.pontoId = entity.getPonto().getId();
        this.userAdminId = entity.getUserAdmin() != null ? entity.getUserAdmin().getId() : null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPontoId() {
        return pontoId;
    }

    public void setPontoId(UUID pontoId) {
        this.pontoId = pontoId;
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

    public UUID getUserAdminId() {
        return userAdminId;
    }

    public void setUserAdminId(UUID userAdminId) {
        this.userAdminId = userAdminId;
    }
}
