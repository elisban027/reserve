// src/components/layout/Header.js
import React from 'react';
import './styles/Header.css';
import logo from '../../assets/logo.png'; // Asegúrate de tener un logo en src/assets/
import { useNavigate } from 'react-router-dom'; // <--- ¡Importa useNavigate AQUI!

/**
 * Componente de encabezado global de la aplicación.
 * Imita la estructura de navegación de sitios profesionales.
 */
const Header = () => {
    const navigate = useNavigate(); // <--- ¡Inicializa useNavigate AQUI!

    const handleUserAvatarClick = () => {
        navigate('/reservas'); // <--- ¡Navega a la ruta /reservas al hacer clic!
    };

    return (
        <header className="app-header"> {/* Asegúrate de que esta clase sea 'app-header' o 'main-header' según tu CSS */}
            <div className="header-top-bar">
                <nav className="header-top-nav">
                    <a href="#servicio-cliente" className="top-nav-link">Servicio al cliente</a>
                    <a href="#afilia-tu-restaurante" className="top-nav-link">Afilia tu restaurante</a>
                    <a href="#crea-tu-web" className="top-nav-link">Crea tu web gratis</a>
                </nav>
                <div className="header-auth-info">
                    <span className="location">Lima, Perú</span>
                    <span className="greeting">Hola, Elisban Royer</span>
                </div>
            </div>

            <div className="header-main-bar">
                <div className="header-logo-container">
                    {/* Aquí podrías tener el logo */}
                    <img src={logo} alt="Mesa 24/7 Logo" className="header-logo" />
                </div>

                <div className="header-search-bar">
                    <div className="search-input-group">
                        <i className="search-icon fas fa-search"></i>
                        <input type="text" placeholder="Buscar restaurantes..." className="search-input" />
                    </div>
                    <button className="search-button">
                        <i className="fas fa-search"></i>
                    </button>
                </div>

                <div className="header-actions">
                    <button className="action-button points-button">
                        Tienes 0 Puntos
                    </button>
                    {/* Aplica el onClick a la "E" o al div que la contiene */}
                    <button className="action-button user-avatar" onClick={handleUserAvatarClick}>E</button> {/* <--- ¡Aplica el onClick AQUI! */}
                </div>
            </div>

            <nav className="header-bottom-nav">
                <ul className="bottom-nav-list">
                    <li><a href="#mejor-calificado" className="bottom-nav-item">Mejor calificado</a></li>
                    <li><a href="#descuentos" className="bottom-nav-item active">Descuentos</a></li>
                    <li><a href="#prime" className="bottom-nav-item">PRIME</a></li>
                    <li><a href="#restaurantes" className="bottom-nav-item">Restaurantes de 500 y 1000 puntos</a></li>
                    <li><a href="#blog" className="bottom-nav-item">Blog</a></li>
                    <li><a href="#programa-puntos" className="bottom-nav-item">Programa de puntos</a></li>
                    <li><a href="#en-tendencia" className="bottom-nav-item">En tendencia</a></li>
                    <li><a href="#nuevos-ingresos" className="bottom-nav-item">Nuevos ingresos</a></li>
                </ul>
            </nav>
        </header>
    );
};

export default Header;