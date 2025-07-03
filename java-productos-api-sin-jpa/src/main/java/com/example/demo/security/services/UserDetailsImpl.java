package com.example.demo.security.services;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombreUsuario;
    private String email;

    @JsonIgnore
    private String passwordHash; // ¡Ahora es passwordHash!

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String nombreUsuario, String email, String passwordHash, // Constructor con passwordHash
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.passwordHash = passwordHash; // Asigna a passwordHash
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        // Usa "ROLE_" como prefijo para los roles de Spring Security
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRol().toUpperCase());

        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);

        return new UserDetailsImpl(
                user.getId(),
                user.getNombreUsuario(),
                user.getEmail(),
                user.getPasswordHash(), // Usa user.getPasswordHash()
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() { // Este método es requerido por UserDetails
        return passwordHash; // Debe devolver el hash almacenado
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}