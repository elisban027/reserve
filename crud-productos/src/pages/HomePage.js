// src/pages/HomePage.js
import React, { useState, useEffect, useCallback } from 'react'; // <--- ¡MUY IMPORTANTE! ESTA LÍNEA DEBE SER ASÍ
import Header from '../components/layout/Header';
import Sidebar from '../components/layout/Sidebar';
import Footer from '../components/layout/Footer'; // <--- Asegúrate de que esta línea esté presente
import Card from '../components/ui/Card';
import ReservaList from '../components/ReservaList';

import Modal from '../components/Modal';
import { getReservas } from '../services/api';
import './styles/HomePage.css';
import foodPlaceholder from '../assets/food-placeholder.jpeg'; // <--- Asegúrate de que este archivo exista en src/assets

const HomePage = () => {
    // Estados para la gestión de reservas
    const [reservas, setReservas] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [currentReserva, setCurrentReserva] = useState(null);

    // Función para cargar las reservas desde la API
    const fetchReservas = useCallback(async () => {
        try {
            const response = await getReservas();
            setReservas(response.data);
        } catch (error) {
            console.error("Error al cargar las reservas:", error);
            alert("No se pudieron cargar las reservas. Intenta recargar la página.");
        }
    }, []);

    // Efecto para cargar las reservas cuando el componente se monta
    useEffect(() => {
        fetchReservas();
    }, [fetchReservas]);

    // Manejador para abrir el modal de creación de nueva reserva
    const handleCreateNew = () => {
        setCurrentReserva(null);
        setIsModalOpen(true);
    };

    // Manejador para abrir el modal de edición de reserva
    const handleEditReserva = (reserva) => {
        setCurrentReserva(reserva);
        setIsModalOpen(true);
    };

    // Manejador para cerrar el modal
    const closeModal = () => {
        setIsModalOpen(false);
        setCurrentReserva(null);
    };

    return (
        //HomePage ahora solo renderiza el Sidebar y el contenido principal
        <div className="home-layout-content"> {/* Nueva clase para el contenedor interno de HomePage */}
            <Sidebar /> {/* El Sidebar ahora está dentro de HomePage */}

            <main className="content-area"> {/* Mantener esta clase para los estilos del contenido */}
                {/* Sección destacada con tarjetas de "restaurantes" de ejemplo */}
                <section className="highlight-section">
                    <h2>Tenemos 58 restaurantes disponibles</h2>
                    <div className="restaurant-cards-grid">
                        {/* ... tus tarjetas de Card ... */}
                        <Card className="restaurant-card">
                            <span className="card-badge">20% Off</span>
                            <img src={foodPlaceholder} alt="Chifa Yenta" className="card-image" />
                            <div className="card-content">
                                <h3 className="card-title">Chifa Yenta</h3>
                                <p className="card-description">Chifa | Miraflores</p>
                                <p className="card-description"><i className="fas fa-map-marker-alt"></i> Av. Santa Cruz 655</p>
                                <a href="#ver-mapa" className="card-link"><i className="fas fa-map"></i> Ver mapa</a>
                            </div>
                            <div className="card-footer">
                                <div className="rating">
                                    <span className="rating-score">5.0</span>
                                    <i className="fas fa-star rating-icon"></i>
                                    <span className="reviews">(157 reviews)</span>
                                </div>
                                <div className="price">S/ 55</div>
                            </div>
                        </Card>
                        <Card className="restaurant-card">
                            <span className="card-badge">Nuevo</span>
                            <img src={foodPlaceholder} alt="Sabor Peruano" className="card-image" />
                            <div className="card-content">
                                <h3 className="card-title">Sabor Peruano</h3>
                                <p className="card-description">Comida Criolla | Barranco</p>
                                <p className="card-description"><i className="fas fa-map-marker-alt"></i> Calle Unión 123</p>
                                <a href="#ver-mapa-2" className="card-link"><i className="fas fa-map"></i> Ver mapa</a>
                            </div>
                            <div className="card-footer">
                                <div className="rating">
                                    <span className="rating-score">4.8</span>
                                    <i className="fas fa-star rating-icon"></i>
                                    <span className="reviews">(80 reviews)</span>
                                </div>
                                <div className="price">S/ 45</div>
                            </div>
                        </Card>
                    </div>
                </section>

                {/* La sección de gestión de reservas se mueve a ReservasPage, así que la ELIMINAMOS de aquí */}
                {/* <section className="reservation-management-section"> ... </section> */}

            </main>
            {/* El modal también se ELIMINA de HomePage, ahora está en ReservasPage */}
            {/* <Modal ...> </Modal> */}

            {/* Header y Footer también se ELIMINAN de HomePage, ahora están en App.js */}
            {/* <Header /> */}
            {/* <Footer /> */}
        </div>
    );
};

export default HomePage;

