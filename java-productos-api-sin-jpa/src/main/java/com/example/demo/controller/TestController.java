package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test") // Base URL para este controlador
public class TestController {

    @GetMapping("/all") // Este es el endpoint para http://localhost:3006/api/test/all
    public String allAccess() {
        return "Contenido público.";
    }

    @GetMapping("/user") // Endpoint para /api/test/user
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ADMIN')") // Protegido por rol
    public String userAccess() {
        return "Contenido solo para usuarios autenticados (CLIENTE o ADMIN).";
    }

    @GetMapping("/admin") // Endpoint para /api/test/admin
    @PreAuthorize("hasRole('ADMIN')") // Protegido por rol
    public String adminAccess() {
        return "Contenido solo para administradores (ADMIN).";
    }
}