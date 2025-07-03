// src/components/layout/Footer.js
import React from 'react';
import './styles/Footer.css'; // Importa los estilos del footer

/**
 * Componente de pie de página (footer) para la aplicación.
 * Contiene información de contacto, enlaces útiles y derechos de autor.
 */
const Footer = () => {
    return (
        <footer className="app-footer">
            <div className="footer-content">
                <div className="footer-section footer-about">
                    <h3 className="footer-heading">Gestión de Reservas</h3>
                    <p>Simplificando la administración de reservas para restaurantes, hoteles y negocios de servicios.</p>
                    <p className="contact-info">
                        <i className="fas fa-map-marker-alt"></i> Miraflores, Lima, Perú<br />
                        <i className="fas fa-envelope"></i> info@gestionreservas.com<br />
                        <i className="fas fa-phone"></i> +51 987 654 321
                    </p>
                </div>

                <div className="footer-section footer-links">
                    <h3 className="footer-heading">Enlaces Rápidos</h3>
                    <ul>
                        <li><a href="#home" className="footer-link">Inicio</a></li>
                        <li><a href="#reservas" className="footer-link">Ver Reservas</a></li>
                        <li><a href="#nosotros" className="footer-link">Sobre Nosotros</a></li>
                        <li><a href="#contacto" className="footer-link">Contacto</a></li>
                        <li><a href="#privacidad" className="footer-link">Política de Privacidad</a></li>
                    </ul>
                </div>

                <div className="footer-section footer-social">
                    <h3 className="footer-heading">Síguenos</h3>
                    <div className="social-icons">
                        <a href="https://facebook.com" target="_blank" rel="noopener noreferrer" className="social-icon" aria-label="Facebook">
                            <i className="fab fa-facebook-f"></i>
                        </a>
                        <a href="https://instagram.com" target="_blank" rel="noopener noreferrer" className="social-icon" aria-label="Instagram">
                            <i className="fab fa-instagram"></i>
                        </a>
                        <a href="https://twitter.com" target="_blank" rel="noopener noreferrer" className="social-icon" aria-label="Twitter">
                            <i className="fab fa-twitter"></i>
                        </a>
                        <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer" className="social-icon" aria-label="LinkedIn">
                            <i className="fab fa-linkedin-in"></i>
                        </a>
                    </div>
                </div>
            </div>

            <div className="footer-bottom">
                <p>&copy; {new Date().getFullYear()} Gestión de Reservas. Todos los derechos reservados.</p>
            </div>
        </footer>
    );
};

export default Footer;