package com.bruno.SistemaPonto.services;

import com.bruno.SistemaPonto.dto.FolhaPontoDTO;
import com.bruno.SistemaPonto.dto.SolicitacaoDTO;
import com.bruno.SistemaPonto.entities.FolhaPonto;
import com.bruno.SistemaPonto.entities.Solicitacao;
import com.bruno.SistemaPonto.entities.StatusSolicitacao;
import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.repositories.FolhaPontoRepository;
import com.bruno.SistemaPonto.repositories.SolicitacaoRepository;
import com.bruno.SistemaPonto.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private FolhaPontoRepository folhaPontoRepository;

    @Autowired
    private FolhaPontoService folhaPontoService;

    @Autowired
    private UserRepository userRepository;

    public List<SolicitacaoDTO> findAll() {
        List<Solicitacao> result = solicitacaoRepository.findAll();
        return result.stream()
                .map(SolicitacaoDTO::new)
                .toList();
    }

    public List<SolicitacaoDTO> findByUserId(UUID userId) {
        List<Solicitacao> result = solicitacaoRepository.findByUserId(userId);
        return result.stream()
                .map(SolicitacaoDTO::new)
                .toList();
    }

    public SolicitacaoDTO criarSolicitacao(SolicitacaoDTO data) {
        User user = userRepository.findById(data.getUserId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        FolhaPonto ponto = folhaPontoRepository.findById(data.getPontoId()).orElseThrow(() -> new RuntimeException("Ponto não encontrado"));
        String dia = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Solicitacao solicitacao = new Solicitacao(
                user,
                ponto,
                data.getDiaPontoOriginal(),
                data.getEntradaManhaOriginal(),
                data.getSaidaManhaOriginal(),
                data.getEntradaTardeOriginal(),
                data.getSaidaTardeOriginal(),
                data.getEntradaManhaNovo(),
                data.getSaidaManhaNovo(),
                data.getEntradaTardeNovo(),
                data.getSaidaTardeNovo(),
                data.getMotivo(),
                dia,
                null
        );

        solicitacao = solicitacaoRepository.save(solicitacao);

        return new SolicitacaoDTO(solicitacao);
    }

    @Transactional
    public SolicitacaoDTO processarSolicitacao(UUID solicitacaoId, UUID userAdminId, boolean aprovada) {
        Solicitacao solicitacao = solicitacaoRepository.findById(solicitacaoId).orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        if (!solicitacao.getStatus().equals(StatusSolicitacao.PENDENTE)) {
            throw new RuntimeException("Solicitação já foi processada");
        }

        User userAdmin = userRepository.findById(userAdminId).orElseThrow(() -> new RuntimeException("Admin não encontrado"));
        solicitacao.setUserAdmin(userAdmin);
        solicitacao.setStatus(aprovada ? StatusSolicitacao.APROVADA : StatusSolicitacao.REJEITADA);

        if (aprovada) {
            FolhaPontoDTO novoPonto = new FolhaPontoDTO();
            if (solicitacao.getEntradaManhaNovo() != null) novoPonto.setEntradaManha(solicitacao.getEntradaManhaNovo());
            if (solicitacao.getSaidaManhaNovo() != null) novoPonto.setSaidaManha(solicitacao.getSaidaManhaNovo());
            if (solicitacao.getEntradaTardeNovo() != null) novoPonto.setEntradaTarde(solicitacao.getEntradaTardeNovo());
            if (solicitacao.getSaidaTardeNovo() != null) novoPonto.setSaidaTarde(solicitacao.getSaidaTardeNovo());

            folhaPontoService.updatePonto(solicitacao.getUser().getId(), solicitacao.getDiaPontoOriginal(), novoPonto);
        }

        solicitacaoRepository.save(solicitacao);
        return new SolicitacaoDTO(solicitacao);
    }
}
