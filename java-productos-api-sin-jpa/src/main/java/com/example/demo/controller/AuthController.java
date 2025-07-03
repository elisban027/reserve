package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.payload.request.LoginRequest;
import com.example.demo.security.payload.request.SignupRequest;
import com.example.demo.security.payload.response.JwtResponse;
import com.example.demo.security.payload.response.MessageResponse;
import com.example.demo.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getNombreUsuario(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByNombreUsuario(signUpRequest.getNombreUsuario())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡El nombre de usuario ya está en uso!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡El email ya está en uso!"));
        }

        String userRol = signUpRequest.getRol() != null ? signUpRequest.getRol() : "CLIENTE";
        if (userRol.startsWith("ROLE_")) {
            userRol = userRol.substring(5);
        }

        // Crear nueva cuenta de usuario, incluyendo nombreCompleto y telefono
        // Si SignupRequest no tiene estos campos, considera ponerlos como null o vacíos
        User user = new User(signUpRequest.getNombreUsuario(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), // La contraseña se encripta aquí
                signUpRequest.getNombreCompleto(), // Si SignupRequest tiene getNombreCompleto
                signUpRequest.getTelefono(),       // Si SignupRequest tiene getTelefono
                userRol.toUpperCase());

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("¡Usuario registrado exitosamente!"));
    }
}