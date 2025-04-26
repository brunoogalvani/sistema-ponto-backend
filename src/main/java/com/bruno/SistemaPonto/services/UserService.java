package com.bruno.SistemaPonto.services;

import com.bruno.SistemaPonto.dto.UserDTO;
import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
