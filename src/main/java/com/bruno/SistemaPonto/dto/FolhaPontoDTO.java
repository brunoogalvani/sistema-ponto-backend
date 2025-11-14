package com.bruno.SistemaPonto.dto;

import com.bruno.SistemaPonto.entities.FolhaPonto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@NoArgsConstructor
public class FolhaPontoDTO {

    private UUID id;
    private UUID userId;
    private String dia;
    private String entradaManha;
    private String saidaManha;
    private String entradaTarde;
    private String saidaTarde;
    private String totalHoras;

    public FolhaPontoDTO(FolhaPonto entity){
        entity.atualizarTotalHoras();
        BeanUtils.copyProperties(entity, this);
        this.totalHoras = entity.getTotalHoras();
        this.userId = entity.getUser().getId();
    }
}
