package com.bruno.SistemaPonto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditUserDTO {
    private String name;
    private String email;
    private String password;
}
