package com.example.demo.security.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String nombreUsuario;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password; // Este es el password en texto plano que envía el cliente

    // Campos adicionales para nombre_completo y telefono
    private String nombreCompleto;
    private String telefono;

    private String rol;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() { // Getter para nombreCompleto
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) { // Setter para nombreCompleto
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() { // Getter para telefono
        return telefono;
    }

    public void setTelefono(String telefono) { // Setter para telefono
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}