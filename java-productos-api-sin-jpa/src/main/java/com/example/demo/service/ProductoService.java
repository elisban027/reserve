package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public void saveProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void updateProducto(Producto producto) {
        productoRepository.update(producto);
    }

    public void deleteProducto(Long id) {
        productoRepository.delete(id);
    }
}
