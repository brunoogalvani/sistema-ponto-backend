package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.dto.FolhaPontoDTO;
import com.bruno.SistemaPonto.entities.FolhaPonto;
import com.bruno.SistemaPonto.repositories.FolhaPontoRepository;
import com.bruno.SistemaPonto.services.FolhaPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pontos")
public class FolhaPontoController {

    @Autowired
    private FolhaPontoRepository folhaPontoRepository;

    @Autowired
    private FolhaPontoService folhaPontoService;

    @PostMapping("/bater/{userId}")
    public ResponseEntity<FolhaPontoDTO> baterPonto(@PathVariable Long userId) {
        FolhaPontoDTO folhaPonto = folhaPontoService.baterPonto(userId);
        return ResponseEntity.ok(folhaPonto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FolhaPontoDTO>> getPontoById(@PathVariable Long userId) {
        List<FolhaPonto> pontos = folhaPontoRepository.findByUserId(userId);

        if (pontos.isEmpty()) {
            throw new RuntimeException("Nenhum registro de ponto deste usuário foi encontrado");
        }

        List<FolhaPontoDTO> dtos = pontos.stream().map(FolhaPontoDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }
}
