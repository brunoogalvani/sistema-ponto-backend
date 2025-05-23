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
import java.util.List;
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

    public FolhaPontoDTO findByUserIdAndDia(UUID userId, String dia) {
        FolhaPonto folhaPonto = folhaPontoRepository.findByUserIdAndDia(userId, dia).orElseThrow(() -> new RuntimeException("Folha ponto não encontrada"));
        return new FolhaPontoDTO(folhaPonto);
    }

    public List<FolhaPontoDTO> findByUserId(UUID userId) {
        List<FolhaPonto> result = folhaPontoRepository.findByUserId(userId);
        return result.stream()
                .map(FolhaPontoDTO::new)
                .toList();
    }

    public FolhaPontoDTO updatePonto(UUID userId, String dia, FolhaPontoDTO novoPonto) {
        FolhaPonto pontoExistente = folhaPontoRepository.findByUserIdAndDia(userId, dia).orElseThrow(() -> new RuntimeException("Folha ponto não encontrada"));

        if (novoPonto.getEntradaManha() != null) {
            pontoExistente.setEntradaManha(novoPonto.getEntradaManha());
        }

        if (novoPonto.getSaidaManha() != null) {
            pontoExistente.setSaidaManha(novoPonto.getSaidaManha());
        }

        if (novoPonto.getEntradaTarde() != null) {
            pontoExistente.setEntradaTarde(novoPonto.getEntradaTarde());
        }

        if (novoPonto.getSaidaTarde() != null) {
            pontoExistente.setSaidaTarde(novoPonto.getSaidaTarde());
        }

        folhaPontoRepository.save(pontoExistente);

        return new FolhaPontoDTO(pontoExistente);
    }
}
