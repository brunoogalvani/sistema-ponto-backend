package com.bruno.SistemaPonto.services;

import com.bruno.SistemaPonto.dto.EditUserDTO;
import com.bruno.SistemaPonto.dto.RegisterDTO;
import com.bruno.SistemaPonto.dto.UserDTO;
import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> result = userRepository.findAll();
        return result.stream()
                .map(UserDTO::new)
                .toList();
    }

    public UserDTO findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UserDTO(user);
    }

    public void registerUser(RegisterDTO data) {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role());

        this.userRepository.save(newUser);
    }

    public void updateUser(UUID id, EditUserDTO dto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new RuntimeException("Usuário não encontrado");

        User user = optionalUser.get();

        if (dto.getName() != null && !dto.getName().isBlank()) {
            user.setName(dto.getName());
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
            user.setPassword(encryptedPassword);
        }

        userRepository.save(user);
    }

    public boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
