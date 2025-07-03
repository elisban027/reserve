package com.example.demo.service;

import com.example.demo.model.Reserva;
import com.example.demo.model.User;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.repository.UserRepository; // Importar UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("reservaService") // Mantén este nombre de bean
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UserRepository userRepository; // Inyectar UserRepository

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva reserva, String username) {
        User user = userRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con nombre: " + username));
        reserva.setUser(user);
        return reservaRepository.save(reserva);
    }

    public Reserva update(Long id, Reserva reservaDetails) {
        return reservaRepository.findById(id)
                .map(reservaExistente -> {
                    reservaExistente.setFecha(reservaDetails.getFecha());
                    reservaExistente.setHora(reservaDetails.getHora());
                    reservaExistente.setNumberOfPeople(reservaDetails.getNumberOfPeople());
                    reservaExistente.setMesa(reservaDetails.getMesa());
                    return reservaRepository.save(reservaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    public void deleteById(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new RuntimeException("Reserva no encontrada con ID: " + id);
        }
        reservaRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return reservaRepository.existsById(id);
    }

    // Método para la seguridad @PreAuthorize
    public boolean isReservaOwnedByCurrentUser(Long reservaId, String username) {
        return reservaRepository.findById(reservaId)
                .map(reserva -> reserva.getUser() != null && reserva.getUser().getNombreUsuario().equals(username))
                .orElse(false);
    }
}