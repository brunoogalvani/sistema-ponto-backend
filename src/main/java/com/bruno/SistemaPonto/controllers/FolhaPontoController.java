package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.dto.FolhaPontoDTO;
import com.bruno.SistemaPonto.entities.FolhaPonto;
import com.bruno.SistemaPonto.repositories.FolhaPontoRepository;
import com.bruno.SistemaPonto.services.FolhaPontoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pontos")
@Tag(name = "Folha Ponto")
public class FolhaPontoController {

    @Autowired
    private FolhaPontoRepository folhaPontoRepository;

    @Autowired
    private FolhaPontoService folhaPontoService;

    @Operation(
            summary = "Registra o ponto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ponto registrado com sucesso", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @PostMapping("/bater/{userId}")
    public ResponseEntity<FolhaPontoDTO> baterPonto(@PathVariable UUID userId) {
        FolhaPontoDTO folhaPonto = folhaPontoService.baterPonto(userId);
        return ResponseEntity.ok(folhaPonto);
    }

    @Operation(
            summary = "Retorna os pontos do usuário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de pontos retornada com sucesso", content = @Content),
                    @ApiResponse(responseCode = "204", description = "Usuário não registrou nenhum ponto", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @GetMapping("/{userId}")
    public ResponseEntity<List<FolhaPontoDTO>> getPontoById(@PathVariable UUID userId) {
        List<FolhaPontoDTO> pontos = folhaPontoService.findByUserId(userId);

        if (pontos.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pontos);
    }

    @Operation(
            summary = "Retorna o ponto do usuário em um dia",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ponto do dia retornado com sucesso", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @GetMapping("/{userId}/{dia}")
    public ResponseEntity<FolhaPontoDTO> getPontoByDay(@PathVariable UUID userId, @PathVariable String dia){
        FolhaPontoDTO folhaPonto = folhaPontoService.findByUserIdAndDia(userId, dia);
        return ResponseEntity.ok(folhaPonto);
    }
}
