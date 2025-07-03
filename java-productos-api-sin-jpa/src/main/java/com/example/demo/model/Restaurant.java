package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "restaurantes") // Confirma el nombre de tu tabla
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "tipo_cocina")
    private String tipoCocina;

    @Column
    private String ubicacion;

    @Column
    private String direccion;

    @Column(precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(name = "numero_reviews")
    private Integer numeroReviews;

    @Column(name = "precio_promedio", precision = 10, scale = 2)
    private BigDecimal precioPromedio;

    @Column(name = "url_imagen")
    private String urlImagen;

    @Column
    private String descripcion;

    @Column
    private String telefono;

    @Column(name = "email_contacto")
    private String emailContacto;

    @Column(name = "horario_apertura")
    private LocalTime horarioApertura;

    @Column(name = "horario_cierre")
    private LocalTime horarioCierre;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public Restaurant() {}

    // Constructor para crear nuevos restaurantes (sin ID y fechaCreacion)
    public Restaurant(String nombre, String tipoCocina, String ubicacion, String direccion, BigDecimal rating, Integer numeroReviews, BigDecimal precioPromedio, String urlImagen, String descripcion, String telefono, String emailContacto, LocalTime horarioApertura, LocalTime horarioCierre) {
        this.nombre = nombre;
        this.tipoCocina = tipoCocina;
        this.ubicacion = ubicacion;
        this.direccion = direccion;
        this.rating = rating;
        this.numeroReviews = numeroReviews;
        this.precioPromedio = precioPromedio;
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.emailContacto = emailContacto;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }

    // Constructor completo (útil para mapear desde la BD)
    public Restaurant(Long id, String nombre, String tipoCocina, String ubicacion, String direccion, BigDecimal rating, Integer numeroReviews, BigDecimal precioPromedio, String urlImagen, String descripcion, String telefono, String emailContacto, LocalTime horarioApertura, LocalTime horarioCierre, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.tipoCocina = tipoCocina;
        this.ubicacion = ubicacion;
        this.direccion = direccion;
        this.rating = rating;
        this.numeroReviews = numeroReviews;
        this.precioPromedio = precioPromedio;
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.emailContacto = emailContacto;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.fechaCreacion = fechaCreacion;
    }

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipoCocina() { return tipoCocina; }
    public void setTipoCocina(String tipoCocina) { this.tipoCocina = tipoCocina; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }
    public Integer getNumeroReviews() { return numeroReviews; }
    public void setNumeroReviews(Integer numeroReviews) { this.numeroReviews = numeroReviews; }
    public BigDecimal getPrecioPromedio() { return precioPromedio; }
    public void setPrecioPromedio(BigDecimal precioPromedio) { this.precioPromedio = precioPromedio; }
    public String getUrlImagen() { return urlImagen; }
    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmailContacto() { return emailContacto; }
    public void setEmailContacto(String emailContacto) { this.emailContacto = emailContacto; }
    public LocalTime getHorarioApertura() { return horarioApertura; }
    public void setHorarioApertura(LocalTime horarioApertura) { this.horarioApertura = horarioApertura; }
    public LocalTime getHorarioCierre() { return horarioCierre; }
    public void setHorarioCierre(LocalTime horarioCierre) { this.horarioCierre = horarioCierre; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}