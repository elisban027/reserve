// auth/authService.js
const USER_STORAGE_KEY = 'user'; // Clave para guardar en localStorage

// Guarda los datos del usuario (incluido el token) en localStorage
export const saveUser = (userData) => {
    localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(userData));
};

// Obtiene los datos del usuario de localStorage
export const getUser = () => {
    const user = localStorage.getItem(USER_STORAGE_KEY);
    return user ? JSON.parse(user) : null;
};

// Elimina los datos del usuario de localStorage (logout)
export const removeUser = () => {
    localStorage.removeItem(USER_STORAGE_KEY);
};

// Obtiene solo el token JWT
export const getAccessToken = () => {
    const user = getUser();
    return user ? user.accessToken : null;
};

// Comprueba si el usuario está logueado (básicamente si hay token)
export const isLoggedIn = () => {
    return getAccessToken() !== null;
};

// Obtiene el rol del usuario
export const getUserRoles = () => {
    const user = getUser();
    // Asumiendo que el objeto user tiene una propiedad 'roles' que es un array de strings
    return user && user.roles ? user.roles : [];
};