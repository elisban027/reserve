package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas") // Confirma que tu tabla se llama "reservas"
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private LocalTime hora;
    private int numberOfPeople;

    @ManyToOne // Relación ManyToOne con User (el usuario que hizo la reserva)
    @JoinColumn(name = "user_id") // Nombre de la columna en la tabla 'reservas'
    private User user; // El usuario que posee esta reserva

    @ManyToOne // Relación ManyToOne con Mesa (la mesa reservada)
    @JoinColumn(name = "mesa_id") // Nombre de la columna en la tabla 'reservas'
    private Mesa mesa;

    public Reserva() {}

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public int getNumberOfPeople() { return numberOfPeople; }
    public void setNumberOfPeople(int numberOfPeople) { this.numberOfPeople = numberOfPeople; }
    public User getUser() { return user; } // Necesario para isReservaOwnedByCurrentUser
    public void setUser(User user) { this.user = user; }
    public Mesa getMesa() { return mesa; }
    public void setMesa(Mesa mesa) { this.mesa = mesa; }
}