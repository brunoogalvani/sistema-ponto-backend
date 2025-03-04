package com.bruno.SistemaPonto.repositories;

import com.bruno.SistemaPonto.dto.FolhaPontoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FolhaPontoRepository extends JpaRepository<FolhaPontoDTO, Long> {
    Optional<FolhaPontoDTO> findByIdAndDia(Long id, LocalDate dia);
}
