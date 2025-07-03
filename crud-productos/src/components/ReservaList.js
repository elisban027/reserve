import React from 'react';
import { deleteReserva } from '../services/api'; // Importa la función para eliminar
import './styles/ReservaList.css'; // Importa los estilos específicos de la lista

/**
 * Componente para mostrar la lista de reservas en una tabla.
 * @param {object} props - Propiedades del componente.
 * @param {Array<object>} props.reservas - Array de objetos de reserva.
 * @param {function} props.onEdit - Función para manejar la edición de una reserva.
 * @param {function} props.fetchReservas - Función para recargar la lista de reservas.
 */
const ReservaList = ({ reservas, onEdit, fetchReservas }) => {
  // Función para manejar la eliminación de una reserva
  const handleDelete = async (id) => {
    // Pide confirmación al usuario antes de eliminar
    if (window.confirm('¿Estás seguro de que quieres eliminar esta reserva de forma permanente?')) {
      try {
        await deleteReserva(id); // Llama a la API para eliminar
        fetchReservas(); // Vuelve a cargar las reservas para actualizar la lista
        alert("Reserva eliminada con éxito.");
      } catch (error) {
        console.error("Error al eliminar la reserva:", error);
        alert("Hubo un error al eliminar la reserva. Por favor, inténtalo de nuevo.");
      }
    }
  };

  return (
      <div className="reserva-list-container">
        {reservas.length === 0 ? (
            // Mensaje si no hay reservas
            <p className="no-reservas-message">
              Actualmente no hay reservas registradas. ¡Haz clic en "Nueva Reserva" para empezar!
            </p>
        ) : (
            // Tabla de reservas si hay datos
            <div className="table-responsive">
              <table className="reserva-table">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>Fecha</th>
                  <th>Hora</th>
                  <th>Cliente</th>
                  <th>Contacto</th>
                  <th>Personas</th>
                  <th>Mesa</th>
                  <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                {reservas.map((reserva) => (
                    <tr key={reserva.id}>
                      <td>{reserva.id}</td>
                      <td>{reserva.fechaReserva}</td>
                      <td>{reserva.horaReserva}</td>
                      <td>{reserva.nombreCliente}</td>
                      <td>{reserva.contactoCliente || 'N/A'}</td> {/* Muestra 'N/A' si no hay contacto */}
                      <td>{reserva.numeroPersonas}</td>
                      <td>{reserva.identificadorMesa}</td>
                      <td className="reserva-actions">
                        <button
                            onClick={() => onEdit(reserva)}
                            className="button button-edit"
                            title="Editar Reserva"
                        >
                          Editar
                        </button>
                        <button
                            onClick={() => handleDelete(reserva.id)}
                            className="button button-delete"
                            title="Eliminar Reserva"
                        >
                          Eliminar
                        </button>
                      </td>
                    </tr>
                ))}
                </tbody>
              </table>
            </div>
        )}
      </div>
  );
};

export default ReservaList;