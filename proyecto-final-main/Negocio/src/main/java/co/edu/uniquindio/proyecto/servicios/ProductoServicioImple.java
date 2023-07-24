package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductoServicioImple implements ProductoServicio {

    private final UsuarioRepo usuarioRepo;
    private final ProductoRepo productoRepo;
    private final CarritoRepo carritoRepo;
    private final ComentarioRepo comentarioRepo;
    private final TransaccionRepo transaccionRepo;
    private final Mapeador mapeador;

    public ProductoServicioImple(UsuarioRepo usuarioRepo, ProductoRepo productoRepo, CarritoRepo carritoRepo, ComentarioRepo comentarioRepo, TransaccionRepo transaccionRepo, Mapeador mapeador) {
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
        this.carritoRepo = carritoRepo;
        this.comentarioRepo = comentarioRepo;
        this.transaccionRepo = transaccionRepo;
        this.mapeador=mapeador;
    }

    @Override
    public void publicarProducto(ProductoPostDTO productoPostDTO) throws Exception {

        Optional<Usuario> usuarioOptional = usuarioRepo.findById(productoPostDTO.getUsuarioCedula());
        if (usuarioOptional.isEmpty()) {
            throw new Exception("No existe un usuario registrado con esa cedula");
        }
        Usuario usuario = usuarioOptional.get();
        if (!usuario.getIsCuentaActiva()) {
            throw new Exception("El usuario no tiene la cuenta activa");
        }

        Producto producto = new Producto(usuario, productoPostDTO.getIsActivo(), productoPostDTO.getImagen(), productoPostDTO.getNombre(), productoPostDTO.getDescripcion(), productoPostDTO.getPrecio(), productoPostDTO.getIsDisponible(), Estado_Producto.SIN_REVISAR, LocalDateTime.now().plusMonths(5), productoPostDTO.getCategorias(), productoPostDTO.getUnidades());
        Producto productoGuardado = productoRepo.save(producto);
        List<Producto> productos = productoRepo.findAllByUsuario_Cedula(usuario.getCedula());
        usuario.setProductos(productos);
        Usuario usuarioActualizado = usuarioRepo.save(usuario);
    }
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    private String generateUniqueFileName(String fileExtension) {
        String uniqueFileName = UUID.randomUUID().toString();
        if (!fileExtension.isEmpty()) {
            uniqueFileName += "." + fileExtension;
        }
        return uniqueFileName;
    }



    @Override
    public void comentarProducto(ComentarioPostDTO comentarioPostDTO) throws Exception {

        Optional<Producto> producto = productoRepo.findById(comentarioPostDTO.getProductoCodigo());
        Optional<Usuario> usuario = usuarioRepo.findById(comentarioPostDTO.getUsuarioCedula());

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con esa cedula");
        }
        if(producto.isEmpty()){
            throw new Exception("No existe un producto con ese codigo");
        }
        if (!usuario.get().getIsCuentaActiva()) {
            throw new Exception("El usuario no esta activo");
        }
        Comentario comentario = new Comentario(comentarioPostDTO.getTexto(), LocalDateTime.now(),usuario.get(),producto.get(), comentarioPostDTO.getCalificacion());
        Comentario comentarioGuardado = comentarioRepo.save(comentario);

        List<Comentario> comentariosProducto = comentarioRepo.findAllByProducto_Codigo(producto.get().getCodigo());
        producto.get().setComentario(comentariosProducto);
        productoRepo.save(producto.get());

        List<Comentario> comentariosUsuario = comentarioRepo.findAllByUsuario_Cedula(usuario.get().getCedula());
        usuario.get().setComentarios(comentariosUsuario);
        usuarioRepo.save(usuario.get());
        }

    @Override
    public void guardarProductoFavorito(GestionFavoritosDTO gestionFavoritosDTO) throws Exception {
        Optional<Producto> producto = productoRepo.findById(gestionFavoritosDTO.getProductoCodigo());
        Optional<Usuario> usuario = usuarioRepo.findById(gestionFavoritosDTO.getUsuarioCedula());

        if (producto.isEmpty()) {
            throw new Exception("El producto no se encuentra registrado");
        }
        if (usuario.isEmpty()) {
            throw new Exception("El usuario no esta registrado");
        }
        if (!usuario.get().getIsCuentaActiva()) {
            throw new Exception("El usuario no esta activo");
        }
        List<Producto> favoritosUsuario = usuarioRepo.buscarFavoritos(usuario.get().getCedula());
        favoritosUsuario.add(producto.get());
        usuario.get().setProductosFavoritos(favoritosUsuario);
        usuarioRepo.save(usuario.get());

    }

    @Override
    public void quitarProductoFavorito(GestionFavoritosDTO gestionFavoritosDTO) throws Exception {
        Optional<Producto> producto = productoRepo.findById(gestionFavoritosDTO.getProductoCodigo());
        Optional<Usuario> usuario = usuarioRepo.findById(gestionFavoritosDTO.getUsuarioCedula());

        if (producto.isEmpty()) {
            throw new Exception("El producto no se encuentra registrado");
        }
        if (usuario.isEmpty()) {
            throw new Exception("El usuario no esta registrado");
        }
        List<Producto> favoritosUsuario = usuarioRepo.buscarFavoritos(usuario.get().getCedula());
        favoritosUsuario.remove(producto.get());
        usuario.get().setProductosFavoritos(favoritosUsuario);
        usuarioRepo.save(usuario.get());
    }

    @Override
    public List<ProductoGetDTO> listarProductoPrecio(RangoPreciosDTO rangoPreciosDTO) throws Exception {
        List<Producto> productos = productoRepo.listarPorRangoDePrecio(rangoPreciosDTO.getPrecioMinimo(), rangoPreciosDTO.getPrecioMaximo());
        if (productos.isEmpty()) {
            throw new Exception("No hay productos de estos precios");
        }
        List<ProductoGetDTO> productoGetDTOS = new ArrayList<>();
        for (Producto producto: productos
             ) {
            ProductoGetDTO productoGetDTO = mapeador.productoAProductoGetDTO(producto);
            productoGetDTOS.add(productoGetDTO);
        }
        return productoGetDTOS;
    }

    @Override
    public List<ProductoGetDTO> buscarProductoNombre(String nombre) throws Exception {
        List<Producto> productos = productoRepo.findAllByNombreContainsIgnoreCase(nombre);
        if (productos.isEmpty()) {
            throw new Exception("No hay productos con estos nombres");
        }
        List<ProductoGetDTO> productoGetDTOS = new ArrayList<>();
        for (Producto producto: productos
        ) {
            ProductoGetDTO productoGetDTO = mapeador.productoAProductoGetDTO(producto);
            productoGetDTOS.add(productoGetDTO);
        }
        return productoGetDTOS;
    }

    @Override
    public List<ProductoGetDTO> listarProductosPublicados(String cedula) throws Exception {
        List<Producto> productos = productoRepo.findAllByUsuario_Cedula(cedula);
        if (productos.isEmpty()) {
            throw new Exception("El usuario con esa cedula no ha publicado productos");
        }
        List<ProductoGetDTO> productoGetDTOS = new ArrayList<>();
        for (Producto producto: productos
        ) {
            ProductoGetDTO productoGetDTO = mapeador.productoAProductoGetDTO(producto);
            productoGetDTOS.add(productoGetDTO);
        }
        return productoGetDTOS;

    }

    @Override
    public List<ProductoGetDTO> listarProductosFavoritos(String cedula) throws Exception {
        List<Producto> productos = productoRepo.obtenerProductosDeUsuarioFavoritos(cedula);
        if (productos.isEmpty()) {
            throw new Exception("El usuario con esa cedula no tiene productos favoritos");
        }
        List<ProductoGetDTO> productoGetDTOS = new ArrayList<>();
        for (Producto producto: productos
        ) {
            ProductoGetDTO productoGetDTO = mapeador.productoAProductoGetDTO(producto);
            productoGetDTOS.add(productoGetDTO);
        }
        return productoGetDTOS;
    }

    @Override
    public void actualizarProducto(Integer codigoProducto,ProductoPostDTO productoPostDTO) throws Exception {
        Optional<Producto> buscado = productoRepo.findById(codigoProducto);
        if (buscado.isEmpty()) {
            throw new Exception("No existe un producto con ese codigo");
        }
        Producto producto = buscado.get();
        producto.setIsActivo(productoPostDTO.getIsActivo());
        producto.setImagen(productoPostDTO.getImagen());
        producto.setNombre(productoPostDTO.getNombre());
        producto.setDescripcion(productoPostDTO.getDescripcion());
        producto.setPrecio(productoPostDTO.getPrecio());
        producto.setIsDisponible(producto.getIsDisponible());
        producto.setCategorias(productoPostDTO.getCategorias());
        producto.setUnidades(productoPostDTO.getUnidades());

        productoRepo.save(producto);

    }

    @Override
    public void eliminarProducto(Integer productoCodigo) throws Exception {
        Optional<Producto> buscado = productoRepo.findById(productoCodigo);
        if (buscado.isPresent()) {
            for (Usuario usuario: usuarioRepo.listaUsuariosFavoritosProductoCodigo(productoCodigo)
                 ) {
                 usuario.getProductosFavoritos().remove(buscado.get());
                 usuarioRepo.save(usuario);
            }
            productoRepo.deleteById(productoCodigo);

        } else {
            throw new Exception("No existe un producto con ese codigo");
        }
    }

    @Override
    public ProductoGetDTO obtenerProducto(Integer codigoProducto) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigoProducto);
        if(producto.isEmpty()){
            throw new Exception("No existe un producto con ese codigo");
        }
        return mapeador.productoAProductoGetDTO(producto.get());
    }

    @Override
    public List<ProductoGetDTO> listarProductos() {
        List<Producto> productos = productoRepo.findAll();
        List<ProductoGetDTO> productoGetDTOS = new ArrayList<>();
        for (Producto producto: productos
             ) {
            ProductoGetDTO productoGetDTO = mapeador.productoAProductoGetDTO(producto);
            productoGetDTOS.add(productoGetDTO);
        }

        return productoGetDTOS;
    }

}
