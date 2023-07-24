package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductoServicio {

    void publicarProducto  (ProductoPostDTO productoPostDTO) throws Exception;

    void comentarProducto (ComentarioPostDTO comentarioPostDTO)  throws Exception;

    void guardarProductoFavorito (GestionFavoritosDTO gestionFavoritosDTO) throws Exception;

    void quitarProductoFavorito (GestionFavoritosDTO gestionFavoritosDTO) throws Exception;

    List<ProductoGetDTO> listarProductoPrecio(RangoPreciosDTO rangoPreciosDTO) throws Exception;

    List<ProductoGetDTO> buscarProductoNombre (String nombre)  throws Exception;

    List<ProductoGetDTO> listarProductosPublicados (String cedula)  throws Exception;
    List<ProductoGetDTO> listarProductosFavoritos (String cedula)  throws Exception;

    void actualizarProducto  (Integer codigoProducto,ProductoPostDTO productoPostDTO) throws Exception;

    void eliminarProducto  (Integer productoCodigo) throws Exception;

    ProductoGetDTO obtenerProducto(Integer codigoProducto) throws Exception;

    List<ProductoGetDTO> listarProductos();

}
