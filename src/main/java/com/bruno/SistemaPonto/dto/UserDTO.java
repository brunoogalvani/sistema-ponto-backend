package com.bruno.SistemaPonto.dto;

import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.entities.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {

    private UUID id;
    private String name;
    private String email;
    private UserRole role;

    public UserDTO(User entity){
        BeanUtils.copyProperties(entity, this);
    }
}
