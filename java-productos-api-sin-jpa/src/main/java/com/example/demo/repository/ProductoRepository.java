package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class ProductoRowMapper implements RowMapper<Producto> {
        @Override
        public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Producto producto = new Producto();
            producto.setId(rs.getLong("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setStock(rs.getInt("stock"));
            return producto;
        }
    }

    public List<Producto> findAll() {
        return jdbcTemplate.query("SELECT * FROM productos", new ProductoRowMapper());
    }

    public Producto findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM productos WHERE id=?", new Object[]{id}, new ProductoRowMapper());
    }

    public int save(Producto producto) {
        return jdbcTemplate.update("INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)",
                producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock());
    }

    public int update(Producto producto) {
        return jdbcTemplate.update("UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=? WHERE id=?",
                producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock(), producto.getId());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM productos WHERE id=?", id);
    }
}
