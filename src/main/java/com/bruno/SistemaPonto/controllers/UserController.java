package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.dto.EditUserDTO;
import com.bruno.SistemaPonto.dto.RegisterDTO;
import com.bruno.SistemaPonto.dto.UserDTO;
import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.repositories.UserRepository;
import com.bruno.SistemaPonto.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Usuários")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Lista os usuários",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso", content = @Content),
                    @ApiResponse(responseCode = "204", description = "Sem usuários no sistema", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> result = userService.findAll();

        if (result.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Retorna um usuário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable UUID id) {
        Optional<UserDTO> user = userService.findById(id);

        if (user.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Atualiza um usuário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody EditUserDTO dto) {
        try {
            userService.updateUser(id, dto);
            return ResponseEntity.ok("Usuário atualizado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Deleta um usuário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            return ResponseEntity.ok("Usuário deletado com sucesso");
        }
        
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Registra um usuário",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Usuário não encontrado/Usuário já existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
            }
    )
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        try {
            userService.registerUser(data);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
