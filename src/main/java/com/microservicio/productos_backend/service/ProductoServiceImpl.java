package com.microservicio.productos_backend.service;

import org.springframework.stereotype.Service;

import com.microservicio.productos_backend.model.dto.ResponseModel;
import com.microservicio.productos_backend.model.entities.Producto;
import com.microservicio.productos_backend.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    //---------GET---------//
    @Override
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Integer id){
        return productoRepository.findById(id);
    }

    //---------POST---------//
    @Override
    public ResponseModel createProducto(Producto producto){
        ResponseModel response;
        var nuevoProducto = productoRepository.save(producto);
        if (nuevoProducto.getIdProducto() > 0) {
            response = new ResponseModel(true, "Producto creado con éxito. Id: " + producto.getIdProducto());
        }else{
            response = new ResponseModel(false, "Se ha producido un error al intentar crear el producto.");
        }
        return response;
    }

    @Override
    public ResponseModel validarProductoPorCodigo(String email){
        String message = "";
        boolean status = false;

        var existeEmail = productoRepository.findBycodigoProducto(email);
        if (!existeEmail.isEmpty()) {
            message = "Ya existe un producto con el código '" + email+ "'";
        }else{
            message = "Puede continuar con la creación del producto.";
            status = true;
        }
        return new ResponseModel(status, message);
    }

    //---------PUT---------//
    @Override
    public ResponseModel updateProducto(Integer id, Producto objProducto){
        ResponseModel response;
        var productoExiste = productoRepository.findById(id);
        if (!productoExiste.isEmpty()) {
            Producto producto = productoExiste.get();
            producto.setCodigoProducto(objProducto.getCodigoProducto());
            producto.setNombre(objProducto.getNombre());
            producto.setDescripcion(objProducto.getDescripcion());
            producto.setCategoria(objProducto.getCategoria());
            producto.setPrecio(objProducto.getPrecio());
            producto.setIdProducto(id);
            //Actualizar producto
            productoRepository.save(producto);
            response = new ResponseModel(true, "Producto actualizado con éxito. Id: " + id);
        }else{
            response = new ResponseModel(true, "Producto no encontrado. Id:" + id);
        }
        return response;
    }

    //---------DELETE---------//
    @Override
    public ResponseModel deleteProducto(Integer id){
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return new ResponseModel(true, "Producto eliminado con éxito");
        }else{
            return new ResponseModel(false, "El producto ingresado no existe");
        }
    }
}

