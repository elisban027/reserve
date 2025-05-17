// src/services/api.js
import axios from 'axios';

const API_URL = 'http://localhost:3000/productos';

export const getProductos = () => axios.get(API_URL);
export const getProductoById = (id) => axios.get(`${API_URL}/${id}`);
export const createProducto = (producto) => axios.post(API_URL, producto);
export const updateProducto = (producto) => axios.put(API_URL, producto);
export const deleteProducto = (id) => axios.delete(`${API_URL}/${id}`);
