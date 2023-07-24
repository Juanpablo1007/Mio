package co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface PublicacionServicio {

    int crearPublicacion(PublicacionDTO publicacionDTO) throws Exception;
    PublicacionGetDTO actualizarPublicacion(int codigoPublicacion, PublicacionDTO publicacionDTO) throws Exception;
    PublicacionGetDTO actualizarEstado(int codigoPublicacion, EstadoPublicacion estado) throws Exception;
    int eliminarPublicacion(int codigoPublicacion) throws Exception;
    PublicacionGetDTO obtenerPublicacionDTO(int codigoPublicacion) throws Exception;
    Publicacion obtenerPublicacion(int codigoPublicacion) throws Exception;
    Producto obtenerProductoPublicacion(int codigoPublicacion) throws Exception;
    List<PublicacionGetDTO> obtenerPublicacionUsuario(int codigoCuenta) throws Exception;
    List<PublicacionGetDTO> eliminarPublicacionVencida(LocalDateTime fechaLimiteCuenta) throws Exception;
    List<PublicacionGetDTO> listar() throws Exception;
    List<PublicacionGetDTO> listarPublicacionEstado(EstadoPublicacion estado) throws Exception;
    List<PublicacionGetDTO> listarPublicacionCategoria(Categoria categoria) throws Exception;
    List<PublicacionGetDTO> listarPublicacionFavoritos(int codigoUsuario) throws Exception;
    List<PublicacionGetDTO> listarPublicacionNombre(String nombre) throws Exception;
    List<PublicacionGetDTO> listarPublicacionPrecio(double precioMinimo, double precioMaximo) throws Exception;
    List<PublicacionGetDTO> listarOfertas() throws Exception;
    List<PublicacionGetDTO> caroBaratoCategoria(Categoria categoria) throws Exception;
    int cantidadVisitas(int codigoPublicacion) throws Exception;
    int actualizarVisitas(VisitaDTO visitaDTO) throws Exception;
}
