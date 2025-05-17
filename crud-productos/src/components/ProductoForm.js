// src/components/ProductoForm.js
import React, { useState, useEffect } from 'react';
import { createProducto, updateProducto } from '../services/api';

const ProductoForm = ({ producto, closeModal, fetchProductos }) => {
  const [formData, setFormData] = useState({
    nombre: '',
    descripcion: '',
    precio: '',
    stock: '',
  });

  useEffect(() => {
    if (producto) {
      setFormData({
        nombre: producto.nombre,
        descripcion: producto.descripcion,
        precio: producto.precio,
        stock: producto.stock,
      });
    }
  }, [producto]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (producto) {
      await updateProducto({ ...formData, id: producto.id });
    } else {
      await createProducto(formData);
    }
    fetchProductos(); // Actualiza la lista de productos después de crear o actualizar
    closeModal();
    setFormData({
      nombre: '',
      descripcion: '',
      precio: '',
      stock: '',
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="nombre" value={formData.nombre} onChange={handleChange} placeholder="Nombre" required />
      <input name="descripcion" value={formData.descripcion} onChange={handleChange} placeholder="Descripción" required />
      <input name="precio" type="number" value={formData.precio} onChange={handleChange} placeholder="Precio" required />
      <input name="stock" type="number" value={formData.stock} onChange={handleChange} placeholder="Stock" required />
      <button type="submit">{producto ? 'Actualizar' : 'Crear'}</button>
    </form>
  );
};

export default ProductoForm;
