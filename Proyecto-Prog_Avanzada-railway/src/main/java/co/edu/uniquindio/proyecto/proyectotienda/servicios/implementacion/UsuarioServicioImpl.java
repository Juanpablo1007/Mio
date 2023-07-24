package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.*;
import co.edu.uniquindio.proyecto.proyectotienda.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private UsuarioRepo usuarioRepo;

    private PublicacionServicio publicacionServicio;
    
    private ProductoServicio productoServicio;

    private CuentaServicio cuentaServicio;

    private EmailServicio emailServicio;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, PublicacionServicio publicacionServicio, CuentaServicio cuentaServicio, ProductoServicio productoServicio, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.publicacionServicio = publicacionServicio;
        this.cuentaServicio = cuentaServicio;
        this.productoServicio = productoServicio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public int crearUsuario(UsuarioDTO usuarioDTO) throws Exception {
        cuentaServicio.existeEmail(usuarioDTO.getEmail());
        Usuario usuario = convertir(usuarioDTO);
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        usuario.setEstado(EstadoCuenta.ACTIVA);
        return usuarioRepo.save(usuario).getCodigo();
    }

    @Override
    @Transactional
    public UsuarioGetDTO actualizarUsuario(int codigoUsuario, UsuarioDTO usuarioDTO) throws Exception {
        validarExiste(codigoUsuario);
        Usuario usuario = convertir(usuarioDTO);
        usuario.setCodigo(codigoUsuario);
        usuario.setEstado(usuarioRepo.buscarEstado(codigoUsuario));
        return convertir(usuarioRepo.save(usuario));
    }

    @Override
    @Transactional
    public int eliminarUsuario(int codigoUsuario) throws Exception {
        validarExiste(codigoUsuario);
        usuarioRepo.deleteById(codigoUsuario);
        return codigoUsuario;
    }

    @Override
    @Transactional
    public void marcarFavorito(int codigoUsuario, int codigoPublicacion) throws Exception {
        validarExiste(codigoUsuario);
        Optional<Usuario> usuarioOpt = usuarioRepo.findById(codigoUsuario);
        List<PublicacionGetDTO> favoritosGetDTO = publicacionServicio.listarPublicacionFavoritos(codigoUsuario);
        List<Publicacion> favoritos = new ArrayList<>();

        for(PublicacionGetDTO pgd : favoritosGetDTO) {
            Publicacion p = new Publicacion();
            p.setCodigo(pgd.getCodigo());
            p.setCuenta(obtenerUsuario(pgd.getCodigoCuenta()));
            p.setProducto(productoServicio.obtenerProducto(pgd.getCodigoProducto()));
            p.setDescuento(pgd.getDescuento());
            p.setEstado(pgd.getEstado());
            p.setComentarios(obtenerComentarios(pgd.getComentarios()));
            p.setDetalleCompras(obtenerDetalles(pgd.getDetalleCompras()));
            p.setFechaLimite(pgd.getFechaLimite());
            favoritos.add(p);
        }
        favoritos.add(publicacionServicio.obtenerPublicacion(codigoPublicacion));
        Usuario usuario = usuarioOpt.get();
        usuario.setFavoritos(favoritos);
        usuarioRepo.save(usuario);
    }

    @Override
    @Transactional
    public void eliminarFavorito(int codigoUsuario, int codigoPublicacion) throws Exception {
        validarExiste(codigoUsuario);
        Optional<Usuario> usuarioOpt = usuarioRepo.findById(codigoUsuario);
        List<PublicacionGetDTO> favoritosGetDTO = publicacionServicio.listarPublicacionFavoritos(codigoUsuario);
        List<Publicacion> favoritos = new ArrayList<>();
        for(PublicacionGetDTO pgd : favoritosGetDTO) {
            Publicacion p = new Publicacion();
            p.setCodigo(pgd.getCodigo());
            p.setCuenta(obtenerUsuario(pgd.getCodigoCuenta()));
            p.setProducto(productoServicio.obtenerProducto(pgd.getCodigoProducto()));
            p.setDescuento(pgd.getDescuento());
            p.setEstado(pgd.getEstado());
            p.setComentarios(obtenerComentarios(pgd.getComentarios()));
            p.setDetalleCompras(obtenerDetalles(pgd.getDetalleCompras()));
            p.setFechaLimite(pgd.getFechaLimite());
            favoritos.add(p);
        }
        Usuario usuario = usuarioOpt.get();
        favoritos.remove(publicacionServicio.obtenerPublicacion(codigoPublicacion));
        usuario.setFavoritos(favoritos);
        usuarioRepo.save(usuario);
    }

    @Override
    public UsuarioGetDTO obtenerUsuarioDTO(int codigoUsuario) throws Exception {
        return convertir(obtener(codigoUsuario));
    }

    @Override
    public Usuario obtenerUsuario(int codigoUsuario) throws Exception {
        return obtener(codigoUsuario);
    }

    public Usuario obtener(int codigoUsuario) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(codigoUsuario);
        if (usuario.isEmpty()) {
            throw new Exception("El código " + codigoUsuario + " no está asociado a ningún usuario");
        }
        return usuario.get();
    }
    private void validarExiste(int codigoUsuario) throws Exception {
        boolean existe = usuarioRepo.existsById(codigoUsuario);
        if (!existe) {
            throw new Exception("El código " + codigoUsuario + " no está asociado a ningún usuario");
        }

    }

    private UsuarioGetDTO convertir(Usuario usuario) {
        return new UsuarioGetDTO(
                usuario.getCodigo(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getContrasenia(),
                usuario.getDireccion(),
                usuario.getTelefono()
        );
    }

    private Usuario convertir(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setContrasenia(usuarioDTO.getContrasenia());


        return usuario;
    }

    private List<Comentario> obtenerComentarios(List<ComentarioGetDTO> comentarios) throws Exception {
        List<Comentario> respuesta = new ArrayList<>();
        for (ComentarioGetDTO cgd : comentarios) {
            Comentario c = new Comentario();
            c.setCodigo(cgd.getCodigo());
            c.setFechaComentario(cgd.getFecha());
            c.setComentario(cgd.getMensaje());
            respuesta.add(c);
            //respuesta.add(comentarioServicio.obtener(cgd.getCodigo()));
        }
        return respuesta;
    }

    private List<DetalleCompra> obtenerDetalles(List<DetalleCompraGetDTO> detalles) throws Exception {
        List<DetalleCompra> respuesta = new ArrayList<>();
        for (DetalleCompraGetDTO dcgd : detalles) {
            DetalleCompra dc = new DetalleCompra();
            dc.setUnidades(dcgd.getUnidades());
            dc.setPrecioUnidad(dcgd.getPrecioUnidad());
            respuesta.add(dc);
            //respuesta.add(comentarioServicio.obtener(cgd.getCodigo()));
        }
        return respuesta;
    }

}
