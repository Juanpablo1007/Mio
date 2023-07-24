package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.*;
import co.edu.uniquindio.proyecto.proyectotienda.repositorios.PublicacionRepo;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.CloudinaryServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.PublicacionServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {

    private PublicacionRepo publicacionRepo;

    private CuentaServicio cuentaServicio;

    private ProductoServicio productoServicio;

    public PublicacionServicioImpl(PublicacionRepo publicacionRepo, CuentaServicio cuentaServicio, ProductoServicio productoServicio) {
        this.publicacionRepo = publicacionRepo;
        this.cuentaServicio = cuentaServicio;
        this.productoServicio = productoServicio;
    }

    @Override
    @Transactional
    public int crearPublicacion(PublicacionDTO publicacionDTO) throws Exception {
        Publicacion publicacion = convertir(publicacionDTO);
        publicacion.setFechaLimite(LocalDateTime.now().plusMonths(1));
        publicacion.setDescuento(0);
        publicacion.setEstado(EstadoPublicacion.SIN_REVISAR);
        return publicacionRepo.save(publicacion).getCodigo();
    }

    @Override
    @Transactional
    public PublicacionGetDTO actualizarPublicacion(int codigoPublicacion, PublicacionDTO publicacionDTO) throws Exception {
        validarPublicacion(codigoPublicacion);
        Producto producto = obtenerProductoPublicacion(codigoPublicacion);
        productoServicio.actualizarProducto(producto.getCodigo(), publicacionDTO.getProducto());
        Publicacion publicacion = convertir(publicacionDTO);
        Publicacion data = obtenerPublicacion(codigoPublicacion);
        publicacion.setEstado(data.getEstado());
        publicacion.setComentarios(data.getComentarios());
        publicacion.setFechaLimite(data.getFechaLimite());
        publicacion.setDetalleCompras(data.getDetalleCompras());
        publicacion.setFavoritos(data.getFavoritos());
        publicacion.setCodigo(codigoPublicacion);
        return convertir(publicacionRepo.save(publicacion));
    }

    @Override
    public PublicacionGetDTO actualizarEstado(int codigoPublicacion, EstadoPublicacion estado) throws Exception {
        Publicacion publicacion = obtenerPublicacion(codigoPublicacion);
        publicacion.setEstado(estado);
        return convertir(publicacionRepo.save(publicacion));
    }

    @Override
    @Transactional
    public int eliminarPublicacion(int codigoPublicacion) throws Exception {
        validarPublicacion(codigoPublicacion);
        Optional<Publicacion> publicacion = publicacionRepo.findById(codigoPublicacion);
        Publicacion eliminada = publicacion.get();
        eliminada.setEstado(EstadoPublicacion.ELIMINADO);
        return publicacionRepo.save(eliminada).getCodigo();
    }

    @Override
    @Transactional(readOnly = true)
    public PublicacionGetDTO obtenerPublicacionDTO(int codigoPublicacion) throws Exception {
        validarPublicacion(codigoPublicacion);
        Optional<Publicacion> publicacion = publicacionRepo.findById(codigoPublicacion);
        LocalDateTime fechaLimite = publicacion.get().getFechaLimite();
        if (fechaLimite.isBefore(LocalDateTime.now())) {
            throw new Exception("La publicación con el codigo " + codigoPublicacion + " expiró el " + fechaLimite);
        }
        return convertir(publicacion.get());
    }

    @Override
    @Transactional(readOnly = true)
    public Publicacion obtenerPublicacion(int codigoPublicacion) throws Exception {
        validarPublicacion(codigoPublicacion);
        Optional<Publicacion> publicacion = publicacionRepo.findById(codigoPublicacion);
        LocalDateTime fechaLimite = publicacion.get().getFechaLimite();
        if (fechaLimite.isBefore(LocalDateTime.now())) {
            throw new Exception("La publicación con el codigo " + codigoPublicacion + " expiró el " + fechaLimite);
        }
        return publicacion.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerProductoPublicacion(int codigoPublicacion) throws Exception {
        validarPublicacion(codigoPublicacion);
        Producto producto = publicacionRepo.obtenerProductoPublicacion(codigoPublicacion);
        return producto;
    }

    @Override
    public List<PublicacionGetDTO> obtenerPublicacionUsuario(int codigoCuenta) throws Exception {
        List<Publicacion> publicaciones = publicacionRepo.obtenerPublicacionUsuario(codigoCuenta);
        List<PublicacionGetDTO> publicacionesGetDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesGetDTO.add(convertir(publicacion));
        }
        return publicacionesGetDTO;
    }

    @Override
    public List<PublicacionGetDTO> eliminarPublicacionVencida(LocalDateTime date) throws Exception {
        List<Publicacion> publicacionesBorrar = publicacionRepo.obtenerPublicacionVencida(date);
        List<PublicacionGetDTO> publicacionesGetDTO = new ArrayList<>();
        if (publicacionesBorrar.isEmpty()) {
            throw new Exception("No hay publicaciones vencidas.");
        }
        for (Publicacion p : publicacionesBorrar) {
            publicacionesGetDTO.add(convertir(p));
        }
        publicacionRepo.deleteAll(publicacionesBorrar);
        return publicacionesGetDTO;
    }

    @Override
    public List<PublicacionGetDTO> listar() throws Exception {
        List<Publicacion> publicaciones = publicacionRepo.listar();
        List<PublicacionGetDTO> publicacionesGetDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesGetDTO.add(convertir(publicacion));
        }
        return publicacionesGetDTO;
    }

    @Override
    public List<PublicacionGetDTO> listarPublicacionEstado(EstadoPublicacion estado) throws Exception {
        List<Publicacion> publicaciones = publicacionRepo.listarPublicacionEstado(estado);
        List<PublicacionGetDTO> publicacionesGetDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesGetDTO.add(convertir(publicacion));
        }
        return publicacionesGetDTO;
    }

    @Override
    public List<PublicacionGetDTO> listarPublicacionCategoria(Categoria categoria) throws Exception {
        List<Publicacion> publicaciones = publicacionRepo.listarPublicacionCategoria(categoria);
        List<PublicacionGetDTO> publicacionesGetDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesGetDTO.add(convertir(publicacion));
        }
        return publicacionesGetDTO;
    }

    @Override
    public List<PublicacionGetDTO> listarPublicacionFavoritos(int codigoUsuario) throws Exception {
        List<Publicacion> publicaciones = publicacionRepo.listarPublicacionFavoritos(codigoUsuario);
        List<PublicacionGetDTO> publicacionesGetDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesGetDTO.add(convertir(publicacion));
        }
        return publicacionesGetDTO;
    }

    @Override
    public List<PublicacionGetDTO> listarPublicacionNombre(String nombre) throws Exception {
        List<Publicacion> publicaciones = publicacionRepo.listarPublicacionNombre(nombre.toLowerCase());
        List<PublicacionGetDTO> publicacionesGetDTO = new ArrayList<>();
        for(Publicacion publicacion : publicaciones){
            publicacionesGetDTO.add(convertir(publicacion));
        }
        return publicacionesGetDTO;
    }

    @Override
    public List<PublicacionGetDTO> listarPublicacionPrecio(double precioMinimo, double precioMaximo) throws Exception {
        List<Publicacion> publicaciones = publicacionRepo.listarPublicacionPrecio(precioMinimo, precioMaximo);
        List<PublicacionGetDTO> publicacionesGetDTO = new ArrayList<>();
        for(Publicacion publicacion : publicaciones){
            publicacionesGetDTO.add(convertir(publicacion));
        }
        return publicacionesGetDTO;
    }

    @Override
    public List<PublicacionGetDTO> listarOfertas() throws Exception {
        List<Publicacion> publicaciones = publicacionRepo.listarOfertas();
        List<PublicacionGetDTO> publicacionesGetDTO = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            publicacionesGetDTO.add(convertir(publicacion));
        }
        return publicacionesGetDTO;
    }

    @Override
    public List<PublicacionGetDTO> caroBaratoCategoria(Categoria categoria) throws Exception {
        List<Publicacion> barato = publicacionRepo.baratoCategoria(categoria);
        List<Publicacion> caro = publicacionRepo.caroCategoria(categoria);
        System.out.println("caro = " + caro.size() + ", barato = " + barato.size());
        List<PublicacionGetDTO> publicaciones = new ArrayList<>();
        publicaciones.add(convertir(barato.get(0)));
        publicaciones.add(convertir(caro.get(0)));
        return publicaciones;
    }

    @Override
    public int cantidadVisitas(int codigoPublicacion) throws Exception {
        validarPublicacion(codigoPublicacion);
        return publicacionRepo.cantidadVisitas(codigoPublicacion);
    }

    @Override
    public int actualizarVisitas(VisitaDTO visitaDTO) throws Exception {
        validarPublicacion(visitaDTO.getCodigoPublicacion());

        return 0;
    }

    private void validarPublicacion(int codigoPublicacion) throws Exception {
        boolean existe = publicacionRepo.existsById(codigoPublicacion);
        if (!existe) {
            throw new Exception("La publicación con el id " + codigoPublicacion + " no existe");


        }
    }

    private Publicacion convertir(PublicacionDTO publicacionDTO) throws Exception {
        Publicacion publicacion = new Publicacion();
        publicacion.setCuenta(cuentaServicio.buscarCuenta(publicacionDTO.getCodigoCuenta()));
        int codProductoCreado = productoServicio.crearProducto(publicacionDTO.getProducto());
        publicacion.setProducto(productoServicio.obtenerProducto(codProductoCreado));
        publicacion.setDescuento(publicacionDTO.getDescuento());
        return publicacion;
    }

    private PublicacionGetDTO convertir(Publicacion publicacion) {
        Producto producto = publicacionRepo.obtenerProductoPublicacion(publicacion.getCodigo());
        List<Comentario> comentarios = publicacion.getComentarios();
        List<ComentarioGetDTO> comentariosGetDTO = new ArrayList<>();
        for (Comentario c : comentarios) {
            ComentarioGetDTO cgd = new ComentarioGetDTO(
                    c.getCodigo(),
                    c.getFechaComentario(),
                    c.getComentario(),
                    c.getUsuario().getCodigo(),
                    c.getPublicacion().getCodigo()
            );
            comentariosGetDTO.add(cgd);
        }
        List<DetalleCompra> detalles = publicacion.getDetalleCompras();
        List<DetalleCompraGetDTO> detallesGetDTO = new ArrayList<>();
        for (DetalleCompra dc : detalles) {
            DetalleCompraGetDTO dcgd = new DetalleCompraGetDTO(
                    dc.getCodigo(),
                    dc.getPublicacion().getCodigo(),
                    dc.getUnidades(),
                    dc.getPrecioUnidad()
            );
            detallesGetDTO.add(dcgd);
        }
        return new PublicacionGetDTO(
                publicacion.getCodigo(),
                publicacion.getCuenta().getCodigo(),
                publicacion.getProducto().getCodigo(),
                publicacion.getFechaLimite(),
                publicacion.getDescuento(),
                publicacion.getEstado(),
                comentariosGetDTO,
                detallesGetDTO,
                new ProductoGetDTO(
                        producto.getCodigo(),
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getUnidadesDisponibles(),
                        producto.getPrecio(),
                        producto.getImagen(),
                        producto.getCategorias()
                ));
    }
}

