package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.EstadoPublicacion;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Moderador;
import co.edu.uniquindio.proyecto.proyectotienda.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.PublicacionServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.UsuarioServicio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeradorServicioImpl implements ModeradorServicio {

    private ModeradorRepo moderadorRepo;

    private PublicacionServicio publicacionServicio;

    private UsuarioServicio usuarioServicio;

    private EmailServicio emailServicio;

    public ModeradorServicioImpl(ModeradorRepo moderadorRepo, PublicacionServicio publicacionServicio, UsuarioServicio usuarioServicio, EmailServicio emailServicio) {
        this.moderadorRepo = moderadorRepo;
        this.publicacionServicio = publicacionServicio;
        this.usuarioServicio = usuarioServicio;
        this.emailServicio = emailServicio;
    }

    @Override
    public ModeradorGetDTO obtenerModeradorDTO(int codigoModerador) throws Exception {
        return convertir(obtenerModerador(codigoModerador));
    }

    @Override
    public Moderador obtenerModerador(int codigoModerador) throws Exception {
        Optional<Moderador> moderador = moderadorRepo.findById(codigoModerador);
        if (moderador.isEmpty()) {
            throw new Exception("El código " + codigoModerador + " no está asociado a ningún moderador");
        }
        return moderador.get();
    }

    @Override
    public PublicacionGetDTO revisarPublicacion(RevisionDTO revisionDTO) throws Exception {
        PublicacionGetDTO revisado = publicacionServicio.actualizarEstado(revisionDTO.getCodigoPublicacion(), revisionDTO.getEstado());
        UsuarioGetDTO usuarioGetDTO = usuarioServicio.obtenerUsuarioDTO(revisionDTO.getCodigoCuenta());
        String cuerpoCorreo = "";
        if(revisionDTO.getEstado().equals(EstadoPublicacion.AUTORIZADO)){
            cuerpoCorreo = "Su publicación cumple con los requerimientos y ha sido autorizada.";
        } else {
            cuerpoCorreo = "Su publicación no cumple con los requerimientos y ha sido denegada.";
        }
        emailServicio.enviarEmail(new EmailDTO("Revisión publicación", cuerpoCorreo, usuarioGetDTO.getEmail()));
        return revisado;
    }

    private ModeradorGetDTO convertir(Moderador moderador) {
        return new ModeradorGetDTO(
                moderador.getNombre(),
                moderador.getApellido(),
                moderador.getEmail(),
                moderador.getTelefono()
        );
    }


}
