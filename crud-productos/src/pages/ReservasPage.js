// src/pages/ReservasPage.js
import React, { useState, useEffect, useCallback } from 'react';
import ReservaList from '../components/ReservaList'; // La lista de reservas existente
import ReservaForm from '../components/ReservaForm'; // El formulario de reservas
import Modal from '../components/Modal'; // El modal
import { getReservas } from '../services/api'; // La función para obtener reservas
import './styles/ReservasPage.css'; // Estilos específicos para esta página (lo crearemos después)

const ReservasPage = () => {
    const [reservas, setReservas] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [currentReserva, setCurrentReserva] = useState(null);

    const fetchReservas = useCallback(async () => {
        try {
            const response = await getReservas();
            setReservas(response.data);
        } catch (error) {
            console.error("Error al cargar las reservas:", error);
            alert("No se pudieron cargar las reservas. Intenta recargar la página.");
        }
    }, []);

    useEffect(() => {
        fetchReservas();
    }, [fetchReservas]);

    const handleCreateNew = () => {
        setCurrentReserva(null);
        setIsModalOpen(true);
    };

    const handleEditReserva = (reserva) => {
        setCurrentReserva(reserva);
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
        setCurrentReserva(null);
    };

    return (
        <div className="reservas-page-container">
            <h1 className="reservas-page-title">Administración de Reservas</h1>
            <section className="reservation-management-section">
                <div className="actions-bar">
                    <h2 className="section-heading">Reservas Existentes</h2>
                    <button
                        onClick={handleCreateNew}
                        className="button button-success"
                    >
                        + Nueva Reserva
                    </button>
                </div>
                <ReservaList
                    reservas={reservas}
                    onEdit={handleEditReserva}
                    fetchReservas={fetchReservas}
                />
            </section>

            <Modal
                isOpen={isModalOpen}
                onClose={closeModal}
                title={currentReserva ? "Editar Reserva" : "Crear Nueva Reserva"}
            >
                <ReservaForm
                    reserva={currentReserva}
                    closeModal={closeModal}
                    fetchReservas={fetchReservas}
                />
            </Modal>
        </div>
    );
};

export default ReservasPage;