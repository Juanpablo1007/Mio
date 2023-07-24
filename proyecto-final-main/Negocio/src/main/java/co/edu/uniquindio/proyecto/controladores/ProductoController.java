package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping( value = "api/productos")
public class ProductoController {

    private final ProductoServicio servicio;
    @PostMapping("/publicar")
    public ResponseEntity<MensajeDTO> publicarProducto(@Valid @RequestBody ProductoPostDTO productoPostDTO) throws Exception {
        servicio.publicarProducto(productoPostDTO);
        return ResponseEntity.status(201).body(new MensajeDTO(HttpStatus.CREATED, false,
                "Producto publicado con exito"));
    }
    @PostMapping("/comentar")
    public ResponseEntity<MensajeDTO> comentarProducto (@Valid @RequestBody ComentarioPostDTO comentarioPostDTO) throws Exception {

        servicio.comentarProducto(comentarioPostDTO);
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Producto comentado con exito" ) );
    }
    @PutMapping("/agregar_favorito")
    public ResponseEntity<MensajeDTO> guardarProductoFavorito (@Valid @RequestBody GestionFavoritosDTO gestionFavoritosDTO) throws Exception {

        servicio.guardarProductoFavorito(gestionFavoritosDTO);
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Producto agregado a favoritos" ) );
    }
    @PutMapping("/eliminar_favorito")
    public ResponseEntity<MensajeDTO> quitarProductoFavorito (@Valid @RequestBody GestionFavoritosDTO gestionFavoritosDTO) throws Exception {

        servicio.quitarProductoFavorito(gestionFavoritosDTO);
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Producto quitado de favoritos" ) );
    }

    @GetMapping("/listar_precio")
    public ResponseEntity<MensajeDTO> listarProductoPrecio(@Valid @RequestBody RangoPreciosDTO rangoPreciosDTO) throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                servicio.listarProductoPrecio(rangoPreciosDTO) ) );
    }
    @GetMapping("/listar_nombre/{nombre}")
    public ResponseEntity<MensajeDTO> listarProductoNombre(@PathVariable String nombre) throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                servicio.buscarProductoNombre(nombre) ) );
    }
    @GetMapping("/listar_productos_vendedor/{cedula}")
    public ResponseEntity<MensajeDTO> listarProductosPublicadosPorVendedor(@PathVariable String cedula) throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                servicio.listarProductosPublicados(cedula)) );
    }
    @GetMapping("/listar_productos_favortitos_usuario/{cedula}")
    public ResponseEntity<MensajeDTO> listarProductosFavoritos(@PathVariable String cedula) throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                servicio.listarProductosFavoritos(cedula)) );
    }
    @PutMapping("/actualizar/{codigo}")
    public ResponseEntity<MensajeDTO> actualizarProducto(@PathVariable Integer codigo,@Valid @RequestBody ProductoPostDTO productoPostDTO) throws Exception {

        servicio.actualizarProducto(codigo,productoPostDTO);
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Producto actualizado con exito") );
    }
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO> eliminar(@PathVariable Integer codigo) throws Exception {
        servicio.eliminarProducto(codigo);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Producto eliminado correctamente" ) );
    }

    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO> obtenerProducto(@PathVariable Integer codigo) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                servicio.obtenerProducto(codigo)));
    }

    @GetMapping("/listar_todos")
    public ResponseEntity<MensajeDTO> listarProductos() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                servicio.listarProductos()));
    }

}
