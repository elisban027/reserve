package com.example.demo.service;

import com.example.demo.model.DisponibilidadMesa;
import com.example.demo.repository.DisponibilidadMesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadMesaService {

    @Autowired
    private DisponibilidadMesaRepository disponibilidadMesaRepository;

    public List<DisponibilidadMesa> findAll() {
        return disponibilidadMesaRepository.findAll();
    }

    public Optional<DisponibilidadMesa> findById(Long id) {
        return disponibilidadMesaRepository.findById(id);
    }

    public DisponibilidadMesa save(DisponibilidadMesa disponibilidad) {
        return disponibilidadMesaRepository.save(disponibilidad);
    }

    public DisponibilidadMesa update(Long id, DisponibilidadMesa disponibilidadDetails) {
        return disponibilidadMesaRepository.findById(id)
                .map(disponibilidad -> {
                    disponibilidad.setFecha(disponibilidadDetails.getFecha());
                    disponibilidad.setHoraInicio(disponibilidadDetails.getHoraInicio());
                    disponibilidad.setHoraFin(disponibilidadDetails.getHoraFin());
                    disponibilidad.setAvailableSeats(disponibilidadDetails.getAvailableSeats());
                    disponibilidad.setMesa(disponibilidadDetails.getMesa());
                    return disponibilidadMesaRepository.save(disponibilidad);
                })
                .orElseThrow(() -> new RuntimeException("Disponibilidad de mesa no encontrada con ID: " + id));
    }

    public boolean existsById(Long id) {
        return disponibilidadMesaRepository.existsById(id);
    }

    public void deleteById(Long id) {
        disponibilidadMesaRepository.deleteById(id);
    }

    // Método de búsqueda específico que tu controlador espera
    public List<DisponibilidadMesa> buscarDisponibilidad(LocalDate fecha, LocalTime hora, Long idRestaurante) {
        if (fecha != null && hora != null && idRestaurante != null) {
            return disponibilidadMesaRepository.findByFechaAndHoraInicioAndMesa_Restaurant_Id(fecha, hora, idRestaurante);
        } else if (fecha != null && hora != null) {
            return disponibilidadMesaRepository.findByFechaAndHoraInicio(fecha, hora);
        } else if (fecha != null) {
            return disponibilidadMesaRepository.findByFecha(fecha);
        }
        return findAll();
    }
}