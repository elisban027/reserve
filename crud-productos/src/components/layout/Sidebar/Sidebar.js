// src/components/layout/Sidebar.js
import React from 'react';
import './styles/Sidebar.css';

/**
 * Componente de barra lateral con opciones de filtro.
 * Imita la sección de filtros de la izquierda en el ejemplo de Mesa 24/7.
 */
const Sidebar = () => {
    return (
        <aside className="sidebar">
            <div className="sidebar-section">
                <h3 className="sidebar-heading">Servicios</h3>
                <div className="checkbox-group">
                    <label className="checkbox-container">
                        <input type="checkbox" checked readOnly /> Reservas
                        <span className="checkmark"></span>
                    </label>
                    <label className="checkbox-container">
                        <input type="checkbox" /> Para recoger
                        <span className="checkmark"></span>
                    </label>
                    <label className="checkbox-container">
                        <input type="checkbox" /> Delivery
                        <span className="checkmark"></span>
                    </label>
                    <label className="checkbox-container">
                        <input type="checkbox" /> Eventos & Experiencias
                        <span className="checkmark"></span>
                    </label>
                </div>
            </div>

            <div className="sidebar-section">
                <h3 className="sidebar-heading">Especiales</h3>
                <ul className="sidebar-list">
                    <li><a href="#descuentos-sidebar" className="sidebar-link">Descuentos %</a></li>
                    <li><a href="#nuevos-sidebar" className="sidebar-link">Nuevos</a></li>
                    <li><a href="#populares-sidebar" className="sidebar-link">Populares</a></li>
                    <li><a href="#promociones-sidebar" className="sidebar-link">Promociones</a></li>
                </ul>
            </div>

            <div className="sidebar-section">
                <h3 className="sidebar-heading">Precio Promedio</h3>
                <div className="price-range">
                    <span className="price-tag">S/ 30 - 60</span>
                    <span className="price-tag active">S/ 60 - 90</span>
                    <span className="price-tag">S/ 90+</span>
                </div>
            </div>

            {/* Puedes añadir más secciones como "Tipo de Cocina", "Ambiente", etc. */}
        </aside>
    );
};

export default Sidebar;