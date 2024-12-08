package com.microservicio.productos_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.productos_backend.model.entities.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findBycodigoProducto(String codigoProducto);
}
