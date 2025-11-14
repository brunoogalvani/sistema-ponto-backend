package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.dto.SolicitacaoDTO;
import com.bruno.SistemaPonto.repositories.SolicitacaoRepository;
import com.bruno.SistemaPonto.services.SolicitacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/solicitacoes")
@Tag(name = "Solicitação")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoService solicitacaoService;

    @Operation(
            summary = "Retorna as solicitações",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de solicitações retornada com sucesso", content = @Content),
                    @ApiResponse(responseCode = "204", description = "Não existe nenhuma solicitação", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @GetMapping
    public ResponseEntity<List<SolicitacaoDTO>> getSolicitacoes() {
        List<SolicitacaoDTO> result = solicitacaoService.findAll();

        if (result.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Retorna as solicitações de um usuário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de solicitações do usuário retornada com sucesso", content = @Content),
                    @ApiResponse(responseCode = "204", description = "Não existe nenhuma solicitação do usuário", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @GetMapping("/{userId}")
    public ResponseEntity<List<SolicitacaoDTO>> getSolicitacoesByUserId(@PathVariable UUID userId) {
        List<SolicitacaoDTO> result = solicitacaoService.findByUserId(userId);

        if (result.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Cria uma solicitação",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Solicitação criada com sucesso", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @PostMapping()
    public ResponseEntity<SolicitacaoDTO> postSolicitacao(@RequestBody SolicitacaoDTO data) {
        SolicitacaoDTO solicitacaoDTO = solicitacaoService.criarSolicitacao(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoDTO);
    }

    @Operation(
            summary = "Processa uma solicitação",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitação processada com sucesso", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @PutMapping("/{solicitacaoId}/processar/{userAdminId}")
    public ResponseEntity<SolicitacaoDTO> processarSolicitacao(@PathVariable UUID solicitacaoId, @PathVariable UUID userAdminId, @RequestParam boolean aprovada) {
        SolicitacaoDTO solicitacaoDTO = solicitacaoService.processarSolicitacao(solicitacaoId, userAdminId, aprovada);

        return ResponseEntity.ok(solicitacaoDTO);
    }
}
