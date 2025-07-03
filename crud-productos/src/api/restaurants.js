// api/restaurants.js
import { API_BASE_URL } from '../config/constants';

const RESTAURANTS_URL = `${API_BASE_URL}/restaurants`;

// Función auxiliar para obtener el token JWT del almacenamiento local
// (Más adelante, esto lo manejará AuthContext/authService)
const getAuthHeaders = () => {
    const user = JSON.parse(localStorage.getItem('user')); // Asumiendo que guardamos el objeto user que contiene el token
    if (user && user.accessToken) {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${user.accessToken}`
        };
    }
    return {
        'Content-Type': 'application/json'
    };
};

// Obtener todos los restaurantes (GET)
export const getAllRestaurants = async () => {
    try {
        const response = await fetch(RESTAURANTS_URL, {
            method: 'GET',
            headers: getAuthHeaders(), // Incluye headers para consistencia, aunque puede ser permitAll()
        });
        if (!response.ok) {
            throw new Error('Error al obtener restaurantes');
        }
        return response.json();
    } catch (error) {
        console.error('Error al obtener todos los restaurantes:', error);
        throw error;
    }
};

// Obtener un restaurante por ID (GET)
export const getRestaurantById = async (id) => {
    try {
        const response = await fetch(`${RESTAURANTS_URL}/${id}`, {
            method: 'GET',
            headers: getAuthHeaders(),
        });
        if (!response.ok) {
            throw new Error(`Error al obtener el restaurante con ID ${id}`);
        }
        return response.json();
    } catch (error) {
        console.error(`Error al obtener el restaurante con ID ${id}:`, error);
        throw error;
    }
};

// Crear un nuevo restaurante (POST - requiere ADMIN)
export const createRestaurant = async (restaurantData) => {
    try {
        const response = await fetch(RESTAURANTS_URL, {
            method: 'POST',
            headers: getAuthHeaders(), // Requiere token de ADMIN
            body: JSON.stringify(restaurantData),
        });
        const data = await response.json();
        if (!response.ok) {
            throw new Error(data.message || 'Error al crear el restaurante');
        }
        return data;
    } catch (error) {
        console.error('Error al crear el restaurante:', error);
        throw error;
    }
};

// Actualizar un restaurante (PUT - requiere ADMIN)
export const updateRestaurant = async (id, restaurantData) => {
    try {
        const response = await fetch(`${RESTAURANTS_URL}/${id}`, {
            method: 'PUT',
            headers: getAuthHeaders(), // Requiere token de ADMIN
            body: JSON.stringify(restaurantData),
        });
        const data = await response.json();
        if (!response.ok) {
            throw new Error(data.message || `Error al actualizar el restaurante con ID ${id}`);
        }
        return data;
    } catch (error) {
        console.error(`Error al actualizar el restaurante con ID ${id}:`, error);
        throw error;
    }
};

// Eliminar un restaurante (DELETE - requiere ADMIN)
export const deleteRestaurant = async (id) => {
    try {
        const response = await fetch(`${RESTAURANTS_URL}/${id}`, {
            method: 'DELETE',
            headers: getAuthHeaders(), // Requiere token de ADMIN
        });
        if (!response.ok) {
            // Para 204 No Content, response.json() daría error.
            // Verificamos el status para no intentar parsear JSON si no hay.
            if (response.status === 204) {
                return null; // O un indicador de éxito
            }
            const data = await response.json(); // Intentar leer el error si lo hay
            throw new Error(data.message || `Error al eliminar el restaurante con ID ${id}`);
        }
        return null; // Éxito sin contenido
    } catch (error) {
        console.error(`Error al eliminar el restaurante con ID ${id}:`, error);
        throw error;
    }
};