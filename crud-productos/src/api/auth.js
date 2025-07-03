// api/auth.js
import { API_BASE_URL } from '../config/constants';

const AUTH_URL = `${API_BASE_URL}/auth`;

// Función para registrar un nuevo usuario
export const signup = async (username, email, password, nombreCompleto, telefono, rol) => {
    try {
        const response = await fetch(`${AUTH_URL}/signup`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, email, password, nombreCompleto, telefono, rol }),
        });

        const data = await response.json();

        if (!response.ok) {
            // Si la respuesta no es 2xx, lanza un error con el mensaje del backend
            throw new Error(data.message || 'Error en el registro');
        }

        return data; // Debería contener un mensaje de éxito
    } catch (error) {
        console.error('Error al registrar:', error);
        throw error; // Propaga el error para que sea manejado por el componente que llama
    }
};

// Función para iniciar sesión
export const signin = async (username, password) => {
    try {
        const response = await fetch(`${AUTH_URL}/signin`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password }),
        });

        const data = await response.json();

        if (!response.ok) {
            // Si la respuesta no es 2xx, lanza un error con el mensaje del backend
            throw new Error(data.message || 'Error al iniciar sesión');
        }

        return data; // Debería contener el token JWT, user details, etc.
    } catch (error) {
        console.error('Error al iniciar sesión:', error);
        throw error;
    }
};