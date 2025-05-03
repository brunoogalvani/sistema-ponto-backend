package com.bruno.SistemaPonto.entities;

public enum StatusSolicitacao {

    PENDENTE("pendente"),
    APROVADA("aprovada"),
    REJEITADA("rejeitada");

    private String status;

    StatusSolicitacao(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
