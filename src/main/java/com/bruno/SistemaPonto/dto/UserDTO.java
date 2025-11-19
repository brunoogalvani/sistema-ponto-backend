package com.bruno.SistemaPonto.dto;

import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.entities.UserRole;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String name;
    private String email;
    private UserRole role;

    public UserDTO() {
    }

    public UserDTO(User entity){
        BeanUtils.copyProperties(entity, this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
