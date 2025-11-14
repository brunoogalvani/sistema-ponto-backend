package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.dto.AuthDTO;
import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.entities.UserRole;
import com.bruno.SistemaPonto.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Autenticação")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Operation(
        summary = "Realiza a autenticação do usuário",
        responses = {
                @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso", content = @Content),
                @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content),
                @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
        }
    )
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data) {

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);

            String email = auth.getName();

            UserDetails userDetails = userRepository.findByEmail(email);

            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
            }

            UUID userId = null;
            UserRole role = null;
            if (userDetails instanceof User) {
                userId = ((User) userDetails).getId();
                role = ((User) userDetails).getRole();
            }

            Map<String, Object> response = new HashMap<>();
            response.put("id", userId);
            response.put("role", role);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
        }
    }
}
