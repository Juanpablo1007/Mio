package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Comentario;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Publicacion;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Usuario;
import co.edu.uniquindio.proyecto.proyectotienda.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.PublicacionServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.UsuarioServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    private ComentarioRepo comentarioRepo;

    private UsuarioServicio usuarioServicio;

    private PublicacionServicio publicacionServicio;

     private EmailServicio emailServicio;

    public ComentarioServicioImpl(ComentarioRepo comentarioRepo, UsuarioServicio usuarioServicio, PublicacionServicio publicacionServicio, EmailServicio emailServicio){
        this.comentarioRepo = comentarioRepo;
        this.usuarioServicio = usuarioServicio;
        this.publicacionServicio = publicacionServicio;
        this.emailServicio = emailServicio;
    }

    @Override
    public int crearComentario(ComentarioDTO comentarioDTO) throws Exception {
        Comentario comentario = convertir(comentarioDTO);
        comentario.setFechaComentario(LocalDateTime.now());
        PublicacionGetDTO publicacion = publicacionServicio.obtenerPublicacionDTO(comentarioDTO.getCodigoPublicacion());
        emailServicio.enviarEmail(new EmailDTO("Nuevo comentario en tu publicación.", "<h1>" + publicacion.getProducto().getNombre() + "</h1><br>" + comentarioDTO.getMensaje(), usuarioServicio.obtenerUsuarioDTO(publicacion.getCodigoCuenta()).getEmail()));
        return comentarioRepo.save(comentario).getCodigo();
    }

    @Override
    public List<ComentarioGetDTO> listarComentariosPublicacion(int codigoPublicacion) throws Exception {
        List<ComentarioGetDTO> comentarios = new ArrayList<>();
        for (Comentario comentario : comentarioRepo.buscarPublicacion(codigoPublicacion)) {
            comentarios.add(convertir(comentario));
        }
        return comentarios;
    }

    @Override
    public Comentario obtener(int codigo) throws Exception {
        Optional<Comentario> comentario = comentarioRepo.findById(codigo);
        return comentario.get();
    }

    private Comentario convertir(ComentarioDTO comentarioDTO) throws Exception {
        Comentario comentario = new Comentario();
        comentario.setComentario(comentarioDTO.getMensaje());
        comentario.setUsuario(usuarioServicio.obtenerUsuario(comentarioDTO.getCodigoUsuario()));
        comentario.setPublicacion(publicacionServicio.obtenerPublicacion(comentarioDTO.getCodigoPublicacion()));
        return comentario;
    }

    private ComentarioGetDTO convertir(Comentario comentario){
        ComentarioGetDTO comentarioGetDTO = new ComentarioGetDTO(
                comentario.getCodigo(),
                comentario.getFechaComentario(),
                comentario.getComentario(),
                comentario.getUsuario().getCodigo(),
                comentario.getPublicacion().getCodigo()
        );
        return comentarioGetDTO;
    }

    private void validar(int codigo) throws Exception {
        boolean existe = comentarioRepo.existsById(codigo);
        if(!existe) {
            throw new Exception("El comentario con el id: " + codigo + " no existe.");
        }
    }
}
