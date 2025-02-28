package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.dto.FolhaPontoDTO;
import com.bruno.SistemaPonto.entities.FolhaPonto;
import com.bruno.SistemaPonto.repositories.FolhaPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/pontos")
public class FolhaPontoController {

    @Autowired
    private FolhaPontoRepository folhaPontoRepository;

    @GetMapping("/{id}")
    public FolhaPonto getPontoById(@PathVariable Long id) {
        return folhaPontoRepository.findById(id).orElseThrow(() -> new RuntimeException("Folha Ponto não Encontrada"));
    }

    @GetMapping("/{id}/{data}")
    public FolhaPonto getPontoByDay(@PathVariable Long id, @RequestParam LocalDate data){
        return folhaPontoRepository.findByIdAndData(id, data).orElseThrow(() -> new RuntimeException("Folha Ponto não Encontrada nesta Data"));
    }
}
