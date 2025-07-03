package com.example.demo.controller;

import com.example.demo.model.DisponibilidadMesa;
import com.example.demo.service.DisponibilidadMesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadMesaController {

    @Autowired
    private DisponibilidadMesaService disponibilidadMesaService;

    @GetMapping // GET /api/disponibilidad
    @PreAuthorize("permitAll()") // O hasRole('USER') para que solo autenticados puedan ver
    public ResponseEntity<List<DisponibilidadMesa>> getAllDisponibilidad() {
        List<DisponibilidadMesa> disponibilidades = disponibilidadMesaService.findAll();
        return new ResponseEntity<>(disponibilidades, HttpStatus.OK);
    }

    // GET /api/disponibilidad/buscar?fecha=2025-07-02&hora=19:00&restaurantId=1
    @GetMapping("/buscar")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<DisponibilidadMesa>> buscarDisponibilidad(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime hora,
            @RequestParam(required = false) Long restaurantId) {

        List<DisponibilidadMesa> disponibilidades = disponibilidadMesaService.buscarDisponibilidad(fecha, hora, restaurantId);
        return new ResponseEntity<>(disponibilidades, HttpStatus.OK);
    }

    @GetMapping("/{id}") // GET /api/disponibilidad/{id}
    @PreAuthorize("permitAll()")
    public ResponseEntity<DisponibilidadMesa> getDisponibilidadById(@PathVariable Long id) {
        Optional<DisponibilidadMesa> disponibilidad = disponibilidadMesaService.findById(id);
        return disponibilidad.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping // POST /api/disponibilidad
    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede crear
    public ResponseEntity<DisponibilidadMesa> createDisponibilidad(@RequestBody DisponibilidadMesa disponibilidad) {
        DisponibilidadMesa createdDisponibilidad = disponibilidadMesaService.save(disponibilidad);
        return new ResponseEntity<>(createdDisponibilidad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // PUT /api/disponibilidad/{id}
    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede actualizar
    public ResponseEntity<DisponibilidadMesa> updateDisponibilidad(@PathVariable Long id, @RequestBody DisponibilidadMesa disponibilidadDetails) {
        try {
            DisponibilidadMesa updatedDisponibilidad = disponibilidadMesaService.update(id, disponibilidadDetails);
            return new ResponseEntity<>(updatedDisponibilidad, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") // DELETE /api/disponibilidad/{id}
    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede eliminar
    public ResponseEntity<HttpStatus> deleteDisponibilidad(@PathVariable Long id) {
        try {
            if (disponibilidadMesaService.existsById(id)) {
                disponibilidadMesaService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}