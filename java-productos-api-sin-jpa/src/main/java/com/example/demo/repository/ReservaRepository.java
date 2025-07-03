package com.example.demo.repository;

import com.example.demo.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Necesario para que ReservaService pueda buscar las reservas de un usuario
    List<Reserva> findByUser_NombreUsuario(String username);
}