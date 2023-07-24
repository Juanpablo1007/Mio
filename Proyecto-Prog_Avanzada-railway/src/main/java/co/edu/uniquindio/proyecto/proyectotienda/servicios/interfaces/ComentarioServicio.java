package co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces;

import co.edu.uniquindio.proyecto.proyectotienda.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.ComentarioGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Comentario;

import java.util.List;

public interface ComentarioServicio {

    int crearComentario(ComentarioDTO comentarioDTO) throws Exception;
    List<ComentarioGetDTO> listarComentariosPublicacion(int codigoProducto) throws Exception;
    Comentario obtener(int codigo) throws Exception;
}
