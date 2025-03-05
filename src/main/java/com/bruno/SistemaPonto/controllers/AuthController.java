package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.dto.AuthDTO;
import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.entities.UserRole;
import com.bruno.SistemaPonto.repositories.UserRepository;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        String login = auth.getName();

        UserDetails userDetails = userRepository.findByLogin(login);

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }

        Long userId = null;
        UserRole role = null;
        if (userDetails instanceof User) {
            userId = ((User) userDetails).getId();
            role = ((User) userDetails).getRole();
        }

        if (userId == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ID do usuário não encontrado");

        Map<String, Object> response = new HashMap<>();
        response.put("id", userId);
        response.put("role", role);

        return ResponseEntity.ok(response);
    }
}
