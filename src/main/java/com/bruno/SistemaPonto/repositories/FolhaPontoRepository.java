package com.bruno.SistemaPonto.repositories;

import com.bruno.SistemaPonto.entities.FolhaPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, UUID> {
    Optional<FolhaPonto> findByUserIdAndDia(UUID userId, String dia);

    List<FolhaPonto> findByUserId(UUID userId);
}
