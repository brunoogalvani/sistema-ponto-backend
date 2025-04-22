package com.bruno.SistemaPonto.dto;

import com.bruno.SistemaPonto.entities.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
