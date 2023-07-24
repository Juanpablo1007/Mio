package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioPostDTO;
import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    void registrarUsuario  (UsuarioPostDTO usuarioPostDTO) throws Exception;

    void actualizarUsuario  (UsuarioPostDTO usuarioPostDTO) throws Exception;

    void eliminarUsuario  (String cedula) throws Exception;

    UsuarioGetDTO buscarUsuario(String cedula) throws Exception;
    List<UsuarioGetDTO> listarUsuarios();

    UsuarioGetDTO ObtenerUsuario(String correo) throws Exception;


}
