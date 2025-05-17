// src/components/ProductoList.js
import React, { useEffect } from 'react';
import { getProductos, deleteProducto } from '../services/api';

const ProductoList = ({ setProducto, openModal, fetchProductos, productos, setProductos }) => {

  useEffect(() => {
    fetchProductos();
  }, [fetchProductos]);

  const handleDelete = async (id) => {
    await deleteProducto(id);
    fetchProductos(); // Actualiza la lista de productos después de eliminar
  };

  const handleEdit = (producto) => {
    setProducto(producto);
    openModal();
  };

  return (
    <div>
      <h2>Lista de Productos</h2>
      <ul>
        {productos.map((producto) => (
          <li key={producto.id}>
            {producto.nombre} - {producto.descripcion} - ${producto.precio} - Stock: {producto.stock}
            
            <button className="delete" onClick={() => handleDelete(producto.id)}>Eliminar</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ProductoList;
