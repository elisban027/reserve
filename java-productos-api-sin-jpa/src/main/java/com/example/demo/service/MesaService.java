package com.example.demo.service;

import com.example.demo.model.Mesa;
import com.example.demo.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> findById(Long id) {
        return mesaRepository.findById(id);
    }

    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Mesa update(Long id, Mesa mesaDetails) {
        return mesaRepository.findById(id)
                .map(mesa -> {
                    mesa.setCapacity(mesaDetails.getCapacity());
                    mesa.setRestaurant(mesaDetails.getRestaurant());
                    return mesaRepository.save(mesa);
                })
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada con ID: " + id));
    }

    public boolean existsById(Long id) {
        return mesaRepository.existsById(id);
    }

    public void deleteById(Long id) {
        mesaRepository.deleteById(id);
    }
}