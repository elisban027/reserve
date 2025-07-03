// src/pages/HomePage/HomePage.js
import React, { useState, useEffect, useCallback } from 'react';
import styles from './HomePage.module.css'; // Importa CSS Module
import Sidebar from '../../components/layout/Sidebar/Sidebar'; // Nueva ruta para Sidebar
import Card from '../../components/ui/Card/Card'; // Nueva ruta para Card
import foodPlaceholder from '../../assets/images/food-placeholder.jpg'; // Nueva ruta para la imagen de placeholder
import { getRestaurants } from '../../api/restaurants'; // Asume que crearemos esta función en api/restaurants.js

const HomePage = () => {
    const [restaurants, setRestaurants] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchRestaurants = useCallback(async () => {
        setLoading(true);
        setError(null);
        try {
            // Aquí llamarías a tu API para obtener los datos reales de los restaurantes
            // Por ahora, usaremos datos de ejemplo para visualizar el layout.
            // const response = await getRestaurants();
            // setRestaurants(response.data);

            // Datos de ejemplo para desarrollo:
            setRestaurants([
                {
                    id: 1,
                    name: "Chifa Yenta",
                    cuisine: "Chifa",
                    location: "Miraflores",
                    address: "Av. Santa Cruz 655",
                    rating: 5.0,
                    reviewsCount: 157,
                    averagePrice: 55,
                    imageUrl: foodPlaceholder,
                    badge: "20% Off"
                },
                {
                    id: 2,
                    name: "Sabor Peruano",
                    cuisine: "Comida Criolla",
                    location: "Barranco",
                    address: "Calle Unión 123",
                    rating: 4.8,
                    reviewsCount: 80,
                    averagePrice: 45,
                    imageUrl: foodPlaceholder,
                    badge: "Nuevo"
                },
                {
                    id: 3,
                    name: "La Trattoria",
                    cuisine: "Italiana",
                    location: "San Isidro",
                    address: "Av. Conquistadores 789",
                    rating: 4.5,
                    reviewsCount: 210,
                    averagePrice: 70,
                    imageUrl: foodPlaceholder,
                    badge: ""
                },
                {
                    id: 4,
                    name: "Sushi House",
                    cuisine: "Japonesa",
                    location: "Surco",
                    address: "El Polo 456",
                    rating: 4.9,
                    reviewsCount: 120,
                    averagePrice: 60,
                    imageUrl: foodPlaceholder,
                    badge: "Popular"
                },
                {
                    id: 5,
                    name: "El Cevichano",
                    cuisine: "Mariscos",
                    location: "La Molina",
                    address: "Av. Raúl Ferrero 100",
                    rating: 4.7,
                    reviewsCount: 95,
                    averagePrice: 80,
                    imageUrl: foodPlaceholder,
                    badge: "Imperdible"
                },
                {
                    id: 6,
                    name: "Fusión Andina",
                    cuisine: "Fusión",
                    location: "Miraflores",
                    address: "Calle Cantuarias 200",
                    rating: 4.6,
                    reviewsCount: 180,
                    averagePrice: 90,
                    imageUrl: foodPlaceholder,
                    badge: "Recomendado"
                }
            ]);
        } catch (err) {
            console.error("Error fetching restaurants:", err);
            setError("No se pudieron cargar los restaurantes. Intenta de nuevo más tarde.");
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        fetchRestaurants();
    }, [fetchRestaurants]);

    if (loading) {
        return <div className={styles.loadingMessage}>Cargando restaurantes...</div>;
    }

    if (error) {
        return <div className={styles.errorMessage}>{error}</div>;
    }

    return (
        // El div app-container y main-content-wrapper ya están en App.js.
        // HomePage solo renderiza su contenido específico.
        <div className={styles.homeLayoutContent}> {/* Usa la clase de CSS Module */}
            <Sidebar />

            <main className={styles.contentArea}> {/* Usa la clase de CSS Module */}
                <section className={styles.highlightSection}>
                    <h2>Tenemos {restaurants.length} restaurantes disponibles</h2>
                    <div className={styles.restaurantCardsGrid}>
                        {restaurants.map(restaurant => (
                            <Card key={restaurant.id} className={styles.restaurantCard}>
                                {restaurant.badge && <span className={styles.cardBadge}>{restaurant.badge}</span>}
                                <img src={restaurant.imageUrl} alt={restaurant.name} className={styles.cardImage} />
                                <div className={styles.cardContent}>
                                    <h3 className={styles.cardTitle}>{restaurant.name}</h3>
                                    <p className={styles.cardDescription}>{restaurant.cuisine} | {restaurant.location}</p>
                                    <p className={styles.cardDescription}><i className="fas fa-map-marker-alt"></i> {restaurant.address}</p>
                                    <a href={`/restaurantes/${restaurant.id}`} className={styles.cardLink}><i className="fas fa-map"></i> Ver mapa</a>
                                </div>
                                <div className={styles.cardFooter}>
                                    <div className={styles.rating}>
                                        <span className={styles.ratingScore}>{restaurant.rating.toFixed(1)}</span>
                                        <i className={`${styles.ratingIcon} fas fa-star`}></i>
                                        <span className={styles.reviews}>({restaurant.reviewsCount} reviews)</span>
                                    </div>
                                    <div className={styles.price}>S/ {restaurant.averagePrice}</div>
                                </div>
                            </Card>
                        ))}
                    </div>
                </section>
                {/* Aquí podrías añadir más secciones como "Más populares", "Nuevos ingresos", etc. */}
            </main>
        </div>
    );
};

export default HomePage;