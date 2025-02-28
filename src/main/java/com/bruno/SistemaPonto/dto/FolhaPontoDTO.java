package com.bruno.SistemaPonto.dto;

import com.bruno.SistemaPonto.entities.FolhaPonto;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalTime;

public class FolhaPontoDTO {

    private Long id;
    private LocalDate data;
    private LocalTime entradaManha;
    private LocalTime saidaManha;
    private LocalTime entradaTarde;
    private LocalTime saidaTarde;

    public FolhaPontoDTO() {
    }

    public FolhaPontoDTO(FolhaPonto entity){
        BeanUtils.copyProperties(entity, this);
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
