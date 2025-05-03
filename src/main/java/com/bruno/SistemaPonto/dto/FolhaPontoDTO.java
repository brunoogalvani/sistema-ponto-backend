package com.bruno.SistemaPonto.dto;

import com.bruno.SistemaPonto.entities.FolhaPonto;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class FolhaPontoDTO {

    private Long id;
    private UUID userId;
    private String dia;
    private String entradaManha;
    private String saidaManha;
    private String entradaTarde;
    private String saidaTarde;
    private String totalHoras;

    public FolhaPontoDTO() {
    }

    public FolhaPontoDTO(FolhaPonto entity){
        entity.atualizarTotalHoras();
        BeanUtils.copyProperties(entity, this);
        this.totalHoras = entity.getTotalHoras();
        this.userId = entity.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
}
