// src/services/api.js
import axios from 'axios';

// URL base de tu backend. ¡Asegúrate de que este puerto sea el correcto!
// Por ejemplo, si tu Spring Boot corre en 8080, sería 'http://localhost:8080/reservas'
const API_URL = 'http://localhost:3002/reservas'; // <-- ¡VERIFICA ESTE PUERTO!

// Funciones para interactuar con la API REST de Reservas
export const getReservas = () => axios.get(API_URL);
export const getReservaById = (id) => axios.get(`${API_URL}/${id}`);
export const createReserva = (reserva) => axios.post(API_URL, reserva);
export const updateReserva = (reserva) => axios.put(API_URL, reserva); // Nota: Tu backend deberá manejar la actualización de reserva con el ID dentro del cuerpo.
export const deleteReserva = (id) => axios.delete(`${API_URL}/${id}`);