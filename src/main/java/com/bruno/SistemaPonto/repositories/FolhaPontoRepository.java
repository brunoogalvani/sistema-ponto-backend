package com.bruno.SistemaPonto.repositories;

import com.bruno.SistemaPonto.entities.FolhaPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, Long> {
    Optional<FolhaPonto> findByIdAndDia(Long id, String dia);
}
