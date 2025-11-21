package com.bruno.SistemaPonto.configs;

import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.entities.UserRole;
import com.bruno.SistemaPonto.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User(
                    "admin",
                    "admin@sistema.com",
                    passwordEncoder.encode("admin"),
                    UserRole.ADMIN
            );

            userRepository.save(admin);
        }
    }
}
