package com.example.demo.controller;

import com.example.demo.model.Mesa;
import com.example.demo.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Mesa>> getAllMesas() {
        List<Mesa> mesas = mesaService.findAll();
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Mesa> getMesaById(@PathVariable Long id) {
        Optional<Mesa> mesa = mesaService.findById(id);
        return mesa.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mesa> createMesa(@RequestBody Mesa mesa) {
        Mesa createdMesa = mesaService.save(mesa);
        return new ResponseEntity<>(createdMesa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mesa> updateMesa(@PathVariable Long id, @RequestBody Mesa mesaDetails) {
        try {
            Mesa updatedMesa = mesaService.update(id, mesaDetails);
            return new ResponseEntity<>(updatedMesa, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteMesa(@PathVariable Long id) {
        try {
            if (mesaService.existsById(id)) {
                mesaService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}