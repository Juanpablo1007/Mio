package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.CarritoGetDTO;
import co.edu.uniquindio.proyecto.dto.CarritoProductosPostDTO;
import co.edu.uniquindio.proyecto.dto.EliminarProductoCarritoDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioGetDTO;

import java.util.List;

public interface CarritoServicio {

    void asignarCarrito(String usuarioCedula) throws Exception;

    void agregarProducto(CarritoProductosPostDTO carritoProductosPostDTO) throws Exception;

    void eliminarProducto(EliminarProductoCarritoDTO eliminarProductoCarritoDTO) throws Exception;

    Double calcularTotalCarrito(String usuarioCedulaCarrito);

    CarritoGetDTO obtenerCarrito(String usuarioCedulaCarrito)throws Exception;

    List<CarritoGetDTO> obtenerCarritos();


}
