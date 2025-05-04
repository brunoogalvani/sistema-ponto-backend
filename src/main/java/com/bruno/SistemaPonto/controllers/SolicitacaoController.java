package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.dto.SolicitacaoDTO;
import com.bruno.SistemaPonto.repositories.SolicitacaoRepository;
import com.bruno.SistemaPonto.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/solicitacoes")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoDTO>> getSolicitacoes() {
        List<SolicitacaoDTO> result = solicitacaoService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<SolicitacaoDTO>> getSolicitacoesByUserId(@PathVariable UUID userId) {
        List<SolicitacaoDTO> result = solicitacaoService.findByUserId(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<SolicitacaoDTO> postSolicitacao(@RequestBody SolicitacaoDTO data) {
        SolicitacaoDTO solicitacaoDTO = solicitacaoService.criarSolicitacao(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoDTO);
    }

    @PutMapping
    public ResponseEntity<SolicitacaoDTO> processarSolicitacao(@PathVariable UUID solicitacaoId, @PathVariable UUID userAdminId, @RequestParam boolean aprovada) {
        SolicitacaoDTO solicitacaoDTO = solicitacaoService.processarSolicitacao(solicitacaoId, userAdminId, aprovada);

        return ResponseEntity.ok(solicitacaoDTO);
    }
}
