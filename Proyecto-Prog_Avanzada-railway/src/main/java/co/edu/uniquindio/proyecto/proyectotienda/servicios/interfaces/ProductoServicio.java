package co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces;

import co.edu.uniquindio.proyecto.proyectotienda.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.PublicacionGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Categoria;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Producto;

import java.util.List;

public interface ProductoServicio {

    int crearProducto(ProductoDTO productoDTO) throws Exception;
    ProductoGetDTO actualizarProducto(int codigoProducto, ProductoDTO productoDTO) throws Exception;
    int eliminarProducto(int codigoProducto) throws Exception;
    ProductoGetDTO obtenerProductoDTO(int codigoProducto) throws Exception;
    Producto obtenerProducto(int codigoProducto) throws Exception;
    ProductoGetDTO actualizarUnidades(int codigoProducto, int unidadesDescontar) throws Exception;
}
