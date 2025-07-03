import React from 'react';
import { createPortal } from 'react-dom';
import './styles/Modal.css'; // Importa los estilos específicos de este modal

/**
 * Componente Modal genérico para mostrar contenido superpuesto.
 * @param {object} props - Propiedades del componente.
 * @param {boolean} props.isOpen - Si el modal debe estar abierto.
 * @param {function} props.onClose - Función para cerrar el modal.
 * @param {React.ReactNode} props.children - Contenido a mostrar dentro del modal.
 * @param {string} [props.title='Detalles'] - Título del modal.
 */
const Modal = ({ isOpen, onClose, children, title = "Detalles" }) => {
    // Si el modal no está abierto, no renderizamos nada
    if (!isOpen) {
        return null;
    }

    // Usamos createPortal para renderizar el modal fuera del árbol DOM principal
    // Esto ayuda con problemas de z-index y overflow
    return createPortal(
        <div className="modal-overlay">
            <div className="modal-content-wrapper">
                <button
                    onClick={onClose}
                    className="modal-close-button"
                    aria-label="Cerrar modal"
                >
                    &times; {/* Carácter 'x' para cerrar */}
                </button>
                <h2 className="modal-title">{title}</h2>
                <div className="modal-body">
                    {children}
                </div>
            </div>
        </div>,
        // Asegúrate de tener un div con id="modal-root" en tu public/index.html
        document.getElementById('modal-root')
    );
};

export default Modal;