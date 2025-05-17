// src/App.js
import React, { useState } from 'react';
import Modal from 'react-modal';
import ProductoList from './components/ProductoList';
import ProductoForm from './components/ProductoForm';
import { getProductos } from './services/api';
import './App.css';

// Configura el elemento raíz para el modal
Modal.setAppElement('#root');

const App = () => {
  const [producto, setProducto] = useState(null);
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [productos, setProductos] = useState([]); // Define setProductos aquí

  const openModal = () => {
    setProducto(null); // Limpiar el producto al abrir el modal para agregar
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
  };

  const fetchProductos = async () => {
    const response = await getProductos();
    setProductos(response.data); // Usa setProductos aquí
  };

  return (
    <div className="container">
      <h1>CRUD de Productos</h1>
      <button onClick={openModal}>Agregar Producto</button>
      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Agregar Producto"
        className="ReactModal__Content"
        overlayClassName="ReactModal__Overlay"
      >
        <h2>{producto ? 'Editar Producto' : 'Agregar Producto'}</h2>
        <ProductoForm producto={producto} closeModal={closeModal} fetchProductos={fetchProductos} />
      </Modal>
      <ProductoList
        setProducto={setProducto}
        openModal={openModal}
        fetchProductos={fetchProductos}
        productos={productos}
        setProductos={setProductos}
      />
    </div>
  );
};

export default App;
