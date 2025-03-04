package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.dto.FolhaPontoDTO;
import com.bruno.SistemaPonto.entities.FolhaPonto;
import com.bruno.SistemaPonto.repositories.FolhaPontoRepository;
import com.bruno.SistemaPonto.services.FolhaPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pontos")
public class FolhaPontoController {

    @Autowired
    private FolhaPontoRepository folhaPontoRepository;

    @Autowired
    private FolhaPontoService folhaPontoService;

    @PostMapping("/bater/{id}")
    public ResponseEntity<FolhaPontoDTO> baterPonto(@PathVariable Long id) {
        FolhaPontoDTO folhaPonto = folhaPontoService.baterPonto(id);
        return ResponseEntity.ok(folhaPonto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolhaPontoDTO> getPontoById(@PathVariable Long id) {
        FolhaPonto folhaPonto = folhaPontoRepository.findById(id).orElseThrow(() -> new RuntimeException("Folha Ponto não Encontrada"));

        FolhaPontoDTO dto = new FolhaPontoDTO(folhaPonto);
        return ResponseEntity.ok(dto);
    }
}
