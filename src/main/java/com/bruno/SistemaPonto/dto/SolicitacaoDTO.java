package com.bruno.SistemaPonto.dto;

import com.bruno.SistemaPonto.entities.Solicitacao;
import com.bruno.SistemaPonto.entities.StatusSolicitacao;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@NoArgsConstructor
public class SolicitacaoDTO {

    private UUID id;
    private UUID userId;
    private String userName;
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
    private String userAdminName;

    public SolicitacaoDTO(Solicitacao entity) {
        BeanUtils.copyProperties(entity, this);
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getName();
        this.pontoId = entity.getPonto().getId();
        this.userAdminId = entity.getUserAdmin() != null ? entity.getUserAdmin().getId() : null;
        this.userAdminName = entity.getUserAdmin() != null ? entity.getUserAdmin().getName() : null;
    }
}
