package com.example.demo.repository;

import com.example.demo.model.DisponibilidadMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface DisponibilidadMesaRepository extends JpaRepository<DisponibilidadMesa, Long> {
    List<DisponibilidadMesa> findByFecha(LocalDate fecha);
    List<DisponibilidadMesa> findByFechaAndHoraInicio(LocalDate fecha, LocalTime horaInicio);
    // Este método asume que tu modelo Mesa tiene una relación con Restaurant.
    List<DisponibilidadMesa> findByFechaAndHoraInicioAndMesa_Restaurant_Id(LocalDate fecha, LocalTime horaInicio, Long restaurantId);
}