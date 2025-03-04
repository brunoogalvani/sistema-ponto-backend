package com.bruno.SistemaPonto.services;

import com.bruno.SistemaPonto.dto.FolhaPontoDTO;
import com.bruno.SistemaPonto.entities.FolhaPonto;
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
        String dia = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Optional<FolhaPonto> registroOpt = folhaPontoRepository.findByIdAndDia(id, dia);

        FolhaPonto registro = registroOpt.orElseGet(() -> {
           FolhaPonto novoPonto = new FolhaPonto();
           novoPonto.setId(id);
           novoPonto.setDia(dia);
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

        folhaPontoRepository.save(registro);

        return new FolhaPontoDTO(registro);
    }
}
