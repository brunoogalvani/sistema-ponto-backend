package com.bruno.SistemaPonto.repositories;

import com.bruno.SistemaPonto.entities.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, UUID> {
    List<Solicitacao> findByUserId(UUID userId);
}
