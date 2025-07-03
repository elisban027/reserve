// src/components/ui/Card.js
import React from 'react';
import './styles/Card.css';

/**
 * Componente de tarjeta genérico y reutilizable.
 * @param {object} props - Propiedades del componente.
 * @param {React.ReactNode} props.children - Contenido interno de la tarjeta.
 * @param {string} [props.className=''] - Clases CSS adicionales para personalizar.
 * @param {function} [props.onClick] - Función a ejecutar al hacer clic en la tarjeta.
 */
const Card = ({ children, className = '', onClick }) => {
    return (
        <div className={`card ${className}`} onClick={onClick}>
            {children}
        </div>
    );
};

export default Card;