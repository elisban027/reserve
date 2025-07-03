package com.example.demo.service;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant restaurantDetails) {
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setNombre(restaurantDetails.getNombre());
                    restaurant.setTipoCocina(restaurantDetails.getTipoCocina());
                    restaurant.setUbicacion(restaurantDetails.getUbicacion());
                    restaurant.setDireccion(restaurantDetails.getDireccion());
                    restaurant.setRating(restaurantDetails.getRating());
                    restaurant.setNumeroReviews(restaurantDetails.getNumeroReviews());
                    restaurant.setPrecioPromedio(restaurantDetails.getPrecioPromedio());
                    restaurant.setUrlImagen(restaurantDetails.getUrlImagen());
                    restaurant.setDescripcion(restaurantDetails.getDescripcion());
                    restaurant.setTelefono(restaurantDetails.getTelefono());
                    restaurant.setEmailContacto(restaurantDetails.getEmailContacto());
                    restaurant.setHorarioApertura(restaurantDetails.getHorarioApertura());
                    restaurant.setHorarioCierre(restaurantDetails.getHorarioCierre());
                    return restaurantRepository.save(restaurant);
                })
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado con ID: " + id));
    }

    public boolean existsById(Long id) {
        return restaurantRepository.existsById(id);
    }

    public void deleteById(Long id) {
        restaurantRepository.deleteById(id);
    }
}