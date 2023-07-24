package co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces;

import co.edu.uniquindio.proyecto.proyectotienda.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Usuario;

public interface UsuarioServicio {

    int crearUsuario(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioGetDTO actualizarUsuario(int codigoUsuario, UsuarioDTO usuarioDTO) throws Exception;

    int eliminarUsuario(int coigoUsuario) throws Exception;

    void marcarFavorito(int codigoUsuario, int codigoPublicacion) throws Exception;

    void eliminarFavorito(int codigoUsuario, int codigoPublicacion) throws Exception;

    UsuarioGetDTO obtenerUsuarioDTO(int codigoUsuario) throws Exception;

    Usuario obtenerUsuario(int codigoUsuario) throws Exception;
}
