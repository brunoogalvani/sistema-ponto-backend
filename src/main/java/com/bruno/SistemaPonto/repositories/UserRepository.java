package com.bruno.SistemaPonto.repositories;

import com.bruno.SistemaPonto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
