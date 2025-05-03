package com.bruno.SistemaPonto.services;

import com.bruno.SistemaPonto.dto.FolhaPontoDTO;
import com.bruno.SistemaPonto.entities.FolhaPonto;
import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.repositories.FolhaPontoRepository;
import com.bruno.SistemaPonto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class FolhaPontoService {

    @Autowired
    private FolhaPontoRepository folhaPontoRepository;

    @Autowired
    private UserRepository userRepository;

    public FolhaPontoDTO baterPonto(UUID userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não Encontrado"));

        String dia = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Optional<FolhaPonto> registroOpt = folhaPontoRepository.findByUserIdAndDia(user.getId(), dia);

        FolhaPonto registro = registroOpt.orElseGet(() -> {
           FolhaPonto novoPonto = new FolhaPonto();
           novoPonto.setUser(user);
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

        registro.atualizarTotalHoras();

        folhaPontoRepository.save(registro);

        return new FolhaPontoDTO(registro);
    }
}
