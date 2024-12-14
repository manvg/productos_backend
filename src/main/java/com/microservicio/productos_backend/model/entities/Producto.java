package com.microservicio.productos_backend.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @Column(name = "categoria")
    @NotNull
    @Size(min = 2, max = 50, message = "Debe tener entre 2 y 100 caracteres")
    private String categoria;

    @Column(name = "nombre")
    @NotNull
    @Size(min = 2, max = 100, message = "Debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Column(name = "descripcion")
    @NotNull
    @Size(min = 2, max = 250, message = "Debe tener entre 2 y 250 caracteres")
    private String descripcion;

    @Column(name = "precio")
    @NotNull
    private int precio;

    @Column(name = "url_imagen")
    private String urlImagen;


    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
