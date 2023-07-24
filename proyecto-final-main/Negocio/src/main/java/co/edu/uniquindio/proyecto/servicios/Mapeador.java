package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CarritoProductosRepo;
import co.edu.uniquindio.proyecto.repositorios.CarritoRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Mapeador {

    private final CarritoProductosRepo carritoProductosRepo;

    public Mapeador(CarritoProductosRepo carritoProductosRepo) {
        this.carritoProductosRepo = carritoProductosRepo;
    }

    public CarritoGetDTO carritoACarritoGetDTO(Carrito carrito){

        List<CarritoProductosGetDTO> carritoProductosGetDTOS = new ArrayList<>();
        for (CarritoProductos producto: carrito.getProductos()
        ) {
            CarritoProductosGetDTO carritoProductosGetDTO = carritoProductosACarritoProductoGetDTO(producto);
            carritoProductosGetDTOS.add(carritoProductosGetDTO);

        }
        CarritoGetDTO carritoGetDTO = new CarritoGetDTO(carrito.getUsuario().getCedula(),carritoProductosGetDTOS,carritoProductosRepo.calcularTotalCarrito(carrito.getUsuario().getCedula()));

        return carritoGetDTO;

    }

    public ModeradorGetDTO moderadorAModeradorGetDTO(Moderador moderador){
        return new ModeradorGetDTO(moderador.getCedula(), moderador.getNombre(), moderador.getEmail());
    }

    public ProductoGetDTO productoAProductoGetDTO(Producto producto){

        List<ComentarioGetDTO> comentarioGetDTOS = new ArrayList<>();
        for (Comentario comentario: producto.getComentario()
        ) {
            ComentarioGetDTO comentarioGetDTO = comentarioAComentarioGetDTO(comentario);
            comentarioGetDTOS.add(comentarioGetDTO);
        }
        return new ProductoGetDTO(producto.getCodigo(),producto.getUsuario().getCedula(),producto.getUsuario().getNombre(),producto.getUsuariosFavoritos().size(),producto.getIsActivo(),producto.getImagen(),producto.getNombre(),producto.getDescripcion(),producto.getPrecio(),producto.getUnidades(),producto.getEstado(),producto.getCategorias(),comentarioGetDTOS,producto.getIsDisponible(),producto.getFechaLimite());

    }

    public ComentarioGetDTO comentarioAComentarioGetDTO(Comentario comentario){
        return new ComentarioGetDTO(comentario.getTexto(),comentario.getFecha(),comentario.getUsuario().getCedula(),comentario.getProducto().getCodigo(),comentario.getCalificacion());
    }

    public CarritoProductosGetDTO carritoProductosACarritoProductoGetDTO(CarritoProductos carritoProductos){
        ProductoGetDTO productoGetDTO = productoAProductoGetDTO(carritoProductos.getProducto());
        return new CarritoProductosGetDTO(productoGetDTO,carritoProductos.getUnidades());
    }

    public TransaccionGetDTO transaccionADTO(Transaccion transaccion){
        return new TransaccionGetDTO(transaccion.getFecha(),transaccion.getTotal(),transaccion.getUsuarioCompra().getCedula(),transaccion.getUsuarioVenta().getCedula(),transaccion.getMetodoDePago(),productoAProductoGetDTO(transaccion.getProducto()),transaccion.getUnidadesCompradas());
    }

    public UsuarioGetDTO usuarioADTO(Usuario usuario){
         List<ProductoGetDTO> productos = new ArrayList<>();
         List<ProductoGetDTO> productosFavoritos= new ArrayList<>();
         List<ComentarioGetDTO> comentarios= new ArrayList<>();
         List<TransaccionGetDTO> compras= new ArrayList<>();
         List<TransaccionGetDTO> ventas= new ArrayList<>();

        for (Producto producto: usuario.getProductos()
             ) {
            ProductoGetDTO productoGetDTO = productoAProductoGetDTO(producto);
            productos.add(productoGetDTO);
        }
        for (Producto producto: usuario.getProductosFavoritos()
        ) {
            ProductoGetDTO productoGetDTO = productoAProductoGetDTO(producto);
            productosFavoritos.add(productoGetDTO);
        }
        for (Comentario comentario: usuario.getComentarios()
             ) {
            ComentarioGetDTO comentarioGetDTO = comentarioAComentarioGetDTO(comentario);
            comentarios.add(comentarioGetDTO);
        }
        for (Transaccion transaccion: usuario.getCompras()
             ) {
            TransaccionGetDTO transaccionGetDTO = transaccionADTO(transaccion);
            compras.add(transaccionGetDTO);
        }
        for (Transaccion transaccion: usuario.getVentas()
        ) {
            TransaccionGetDTO transaccionGetDTO = transaccionADTO(transaccion);
            ventas.add(transaccionGetDTO);
        }


        return new UsuarioGetDTO(usuario.getCedula(), usuario.getNombre(), usuario.getEmail(), productos,productosFavoritos,carritoACarritoGetDTO(usuario.getCarrito()),usuario.getIsCuentaActiva(),comentarios,compras,ventas, usuario.getTelefono(), usuario.getDireccion());
    }
}
