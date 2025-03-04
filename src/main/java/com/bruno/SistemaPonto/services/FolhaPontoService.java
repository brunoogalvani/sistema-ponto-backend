package com.bruno.SistemaPonto.services;

import com.bruno.SistemaPonto.dto.FolhaPontoDTO;
import com.bruno.SistemaPonto.repositories.FolhaPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class FolhaPontoService {

    @Autowired
    private FolhaPontoRepository folhaPontoRepository;

    public FolhaPontoDTO baterPonto(Long id){
        LocalDate hoje = LocalDate.now();
        Optional<FolhaPontoDTO> registroOpt = folhaPontoRepository.findByIdAndDia(id, hoje);

        FolhaPontoDTO registro = registroOpt.orElseGet(() -> {
           FolhaPontoDTO novoPonto = new FolhaPontoDTO();
           novoPonto.setId(id);
           novoPonto.setDia(hoje);
           return novoPonto;
        });

        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        if (registro.getEntradaManha() == null) {
            registro.setEntradaManha(hora);
        } else if (registro.getSaidaManha() == null) {
            registro.setSaidaManha(hora);
        } else if (registro.getEntradaTarde() == null) {
            registro.setEntradaTarde(hora);
        } else if (registro.getSaidaTarde() == null) {
            registro.setSaidaTarde(hora);
        } else {
            throw new IllegalStateException("Todas as marcações já foram feitas para hoje.");
        }

        return folhaPontoRepository.save(registro);
    }
}
