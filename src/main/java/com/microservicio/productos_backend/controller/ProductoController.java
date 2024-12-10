package com.microservicio.productos_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.productos_backend.model.entities.Producto;
import com.microservicio.productos_backend.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    //---------MÉTODOS GET---------//
    @GetMapping
    public List<Producto> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return productos;
    }

    
    @GetMapping("/{id}")
    public Optional<Producto> getProductoById(@PathVariable Integer id){
        var producto = productoService.getProductoById(id);
        return producto;
    }

    //---------MÉTODOS POST---------//
    @PostMapping
    public ResponseEntity<Object> createProducto(@RequestBody @Valid Producto producto){
        var validacionEmail = productoService.validarProductoPorCodigo(producto.getCodigoProducto());
        if (!validacionEmail.getStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validacionEmail);
        }

        var response = productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

     //---------MÉTODOS PUT---------//
    //Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProducto(@PathVariable Integer id, @RequestBody @Valid Producto producto){
        var response = productoService.updateProducto(id, producto);
        if (response.getStatus()) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    //---------MÉTODOS DELETE---------//
    //Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProducto(@PathVariable Integer id){
        var response = productoService.deleteProducto(id);
        if (!response.getStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

