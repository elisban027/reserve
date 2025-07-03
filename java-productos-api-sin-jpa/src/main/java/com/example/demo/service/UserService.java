package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        // Encriptar la contraseña y asignarla a passwordHash
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash())); // Usar getPasswordHash del objeto User si ya lo tiene
        if (user.getRol() != null && user.getRol().startsWith("ROLE_")) {
            user.setRol(user.getRol().substring(5).toUpperCase());
        } else if (user.getRol() != null) {
            user.setRol(user.getRol().toUpperCase());
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    if (userDetails.getNombreUsuario() != null && !userDetails.getNombreUsuario().isEmpty()) {
                        user.setNombreUsuario(userDetails.getNombreUsuario());
                    }
                    if (userDetails.getEmail() != null && !userDetails.getEmail().isEmpty()) {
                        user.setEmail(userDetails.getEmail());
                    }

                    // Encriptar la nueva contraseña si se proporciona
                    if (userDetails.getPasswordHash() != null && !userDetails.getPasswordHash().isEmpty()) {
                        user.setPasswordHash(passwordEncoder.encode(userDetails.getPasswordHash()));
                    }

                    if (userDetails.getNombreCompleto() != null && !userDetails.getNombreCompleto().isEmpty()) {
                        user.setNombreCompleto(userDetails.getNombreCompleto());
                    }
                    if (userDetails.getTelefono() != null && !userDetails.getTelefono().isEmpty()) {
                        user.setTelefono(userDetails.getTelefono());
                    }

                    if (userDetails.getRol() != null && !userDetails.getRol().isEmpty()) {
                        String newRol = userDetails.getRol();
                        if (newRol.startsWith("ROLE_")) {
                            newRol = newRol.substring(5);
                        }
                        user.setRol(newRol.toUpperCase());
                    }

                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public Boolean existsByNombreUsuario(String nombreUsuario) {
        return userRepository.existsByNombreUsuario(nombreUsuario);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}