package com.bruno.SistemaPonto.repositories;

import com.bruno.SistemaPonto.entities.FolhaPonto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, Long> {
    Optional<FolhaPonto> findByIdAndData(Long id, LocalDate data);
}
