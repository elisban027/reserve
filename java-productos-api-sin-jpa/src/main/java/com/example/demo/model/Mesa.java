package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mesas") // Confirma que tu tabla se llama "mesas"
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacity;

    @ManyToOne // Relación ManyToOne con Restaurant
    @JoinColumn(name = "restaurant_id") // Nombre de la columna en la tabla 'mesas'
    private Restaurant restaurant; // Relación con la entidad Restaurant

    public Mesa() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
}