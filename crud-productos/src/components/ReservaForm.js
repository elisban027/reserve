import React, { useState, useEffect } from 'react';
import { createReserva, updateReserva } from '../services/api';
import './styles/ReservaForm.css'; // Importa los estilos específicos del formulario

/**
 * Componente de formulario para crear o editar una reserva.
 * @param {object} props - Propiedades del componente.
 * @param {object|null} props.reserva - Objeto de reserva si se está editando, null si es nueva.
 * @param {function} props.closeModal - Función para cerrar el modal después de guardar.
 * @param {function} props.fetchReservas - Función para recargar la lista de reservas.
 */
const ReservaForm = ({ reserva, closeModal, fetchReservas }) => {
    // Estado para manejar los datos del formulario
    const [formData, setFormData] = useState({
        fechaReserva: '',
        horaReserva: '',
        numeroPersonas: '',
        nombreCliente: '',
        contactoCliente: '',
        identificadorMesa: '',
    });

    // Sincroniza el estado del formulario con la 'reserva' prop
    // Se ejecuta cuando la prop 'reserva' cambia (ej. al abrir modal para editar)
    useEffect(() => {
        if (reserva) {
            setFormData({
                // Asegúrate de que los valores sean cadenas vacías si son nulos para inputs controlados
                fechaReserva: reserva.fechaReserva || '',
                horaReserva: reserva.horaReserva || '',
                numeroPersonas: reserva.numeroPersonas || '',
                nombreCliente: reserva.nombreCliente || '',
                contactoCliente: reserva.contactoCliente || '',
                identificadorMesa: reserva.identificadorMesa || '',
            });
        } else {
            // Reinicia el formulario si no hay reserva (para crear una nueva)
            setFormData({
                fechaReserva: '', horaReserva: '', numeroPersonas: '',
                nombreCliente: '', contactoCliente: '', identificadorMesa: '',
            });
        }
    }, [reserva]);

    // Maneja los cambios en los inputs del formulario
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            // Convierte numeroPersonas a entero, si no es un número válido, usa cadena vacía
            [name]: (name === 'numeroPersonas') ? parseInt(value, 10) || '' : value,
        });
    };

    // Maneja el envío del formulario
    const handleSubmit = async (e) => {
        e.preventDefault(); // Previene el comportamiento por defecto del formulario

        // Validaciones básicas de campos obligatorios
        if (!formData.fechaReserva || !formData.horaReserva || !formData.numeroPersonas || !formData.nombreCliente || !formData.identificadorMesa) {
            alert("Por favor, rellena todos los campos obligatorios.");
            return;
        }

        try {
            // Si hay un ID en la reserva, actualizamos; de lo contrario, creamos
            if (reserva && reserva.id) {
                await updateReserva({ ...formData, id: reserva.id });
            } else {
                await createReserva(formData);
            }
            fetchReservas(); // Recarga la lista de reservas para mostrar los cambios
            closeModal(); // Cierra el modal
            // Opcional: Limpiar el formulario después de enviar (si no se cierra el modal)
            setFormData({ fechaReserva: '', horaReserva: '', numeroPersonas: '', nombreCliente: '', contactoCliente: '', identificadorMesa: '' });
        } catch (error) {
            console.error("Error al guardar la reserva:", error.response ? error.response.data : error.message);
            alert("Hubo un error al guardar la reserva. Por favor, inténtalo de nuevo.");
        }
    };

    return (
        <form onSubmit={handleSubmit} className="reserva-form">
            <div className="form-group">
                <label htmlFor="fechaReserva">Fecha de la Reserva:</label>
                <input type="date" name="fechaReserva" value={formData.fechaReserva} onChange={handleChange} required />
            </div>
            <div className="form-group">
                <label htmlFor="horaReserva">Hora de la Reserva:</label>
                <input type="time" name="horaReserva" value={formData.horaReserva} onChange={handleChange} required />
            </div>
            <div className="form-group">
                <label htmlFor="nombreCliente">Nombre del Cliente:</label>
                <input type="text" name="nombreCliente" value={formData.nombreCliente} onChange={handleChange} placeholder="Ej: Juan Pérez" required />
            </div>
            <div className="form-group">
                <label htmlFor="contactoCliente">Contacto del Cliente (Email/Teléfono):</label>
                <input type="text" name="contactoCliente" value={formData.contactoCliente} onChange={handleChange} placeholder="Ej: juan@example.com o +51987654321" />
            </div>
            <div className="form-group">
                <label htmlFor="numeroPersonas">Número de Personas:</label>
                <input type="number" name="numeroPersonas" value={formData.numeroPersonas} onChange={handleChange} placeholder="Mínimo 1" min="1" required />
            </div>
            <div className="form-group">
                <label htmlFor="identificadorMesa">Identificador de Mesa:</label>
                <input
                    type="text"
                    name="identificadorMesa"
                    value={formData.identificadorMesa}
                    onChange={handleChange}
                    placeholder="Ej: Mesa A1, Barra 3"
                    // Deshabilita el campo si estamos editando una reserva existente
                    // La lógica '!!(reserva && !reserva.id)' original es confusa.
                    // Simplemente 'reserva && reserva.id' para edición.
                    // Para una nueva reserva (reserva es null), no está deshabilitado.
                    // Si 'reserva' existe y 'reserva.id' existe, entonces estamos editando.
                    className={reserva && reserva.id ? 'input-disabled' : ''}
                    required
                    readOnly={reserva && reserva.id}
                />
            </div>
            <div className="form-actions">
                <button type="submit" className="button button-primary">
                    {reserva && reserva.id ? 'Actualizar Reserva' : 'Confirmar Reserva'}
                </button>
            </div>
        </form>
    );
};

export default ReservaForm;