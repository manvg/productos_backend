package com.microservicio.productos_backend.service;

import java.util.List;
import java.util.Optional;

import com.microservicio.productos_backend.model.dto.ResponseModel;
import com.microservicio.productos_backend.model.entities.Producto;

public interface ProductoService {
    List<Producto> getAllProductos();
    Optional<Producto> getProductoById(Integer id);
    ResponseModel validarProductoPorNombre(String nombre);
    ResponseModel createProducto(Producto usuario);
    ResponseModel updateProducto(Integer id, Producto producto);
    ResponseModel deleteProducto(Integer id);
}
