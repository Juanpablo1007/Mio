package co.edu.uniquindio.proyecto.proyectotienda.controladores;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Categoria;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.EstadoCuenta;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.EstadoPublicacion;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.PublicacionServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/publicacion")
public class PublicacionControlador {

    private final PublicacionServicio publicacionServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearPublicacion(@RequestBody @Valid PublicacionDTO publicacionDTO) throws Exception {
        publicacionServicio.crearPublicacion(publicacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Publicación creada correctamente."));
    }

    @PutMapping("/actualizar/{codigoPublicacion}")
    public ResponseEntity<MensajeDTO> actualizarPublicacion(@PathVariable int codigoPublicacion, @RequestBody @Valid PublicacionDTO publicacionDTO) throws Exception {
        PublicacionGetDTO producto = publicacionServicio.actualizarPublicacion(codigoPublicacion, publicacionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "Publicación actualizada correctamente."));
    }

    @DeleteMapping("/eliminar/{codigoPublicacion}")
    public ResponseEntity<MensajeDTO> eliminarPublicacion(@PathVariable int codigoPublicacion) throws Exception {
        publicacionServicio.eliminarPublicacion(codigoPublicacion);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "Publicación eliminada exitosamente."));
    }

    @GetMapping("/obtener/{codigoPublicacion}")
    public ResponseEntity<MensajeDTO> obtenerPublicacionDTO(@PathVariable int codigoPublicacion) throws Exception {
        PublicacionGetDTO publicacion = publicacionServicio.obtenerPublicacionDTO(codigoPublicacion);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicacion));
    }

    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO> listar() throws Exception {
        List<PublicacionGetDTO> publicaciones = publicacionServicio.listar();
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicaciones));
    }

    @GetMapping("/listar/{codigoCuenta}")
    public ResponseEntity<MensajeDTO> obtenerPublicacionUsuario(@PathVariable int codigoCuenta) throws Exception {
        List<PublicacionGetDTO> publicaciones = publicacionServicio.obtenerPublicacionUsuario(codigoCuenta);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicaciones));
    }

    @GetMapping("/listarEstado")
    public ResponseEntity<MensajeDTO> listarPublicacionEstado(@RequestParam EstadoPublicacion estado) throws Exception {
        List<PublicacionGetDTO> publicaciones = publicacionServicio.listarPublicacionEstado(estado);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicaciones));
    }

    @GetMapping("/listarCategoria")
    public ResponseEntity<MensajeDTO> listarPublicacionCategoria(@RequestParam Categoria categoria) throws Exception {
        List<PublicacionGetDTO> publicaciones = publicacionServicio.listarPublicacionCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicaciones));
    }

    @GetMapping("/favoritos/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> listarPublicacionFavoritos(@PathVariable int codigoUsuario) throws Exception {
        List<PublicacionGetDTO> publicaciones =publicacionServicio.listarPublicacionFavoritos(codigoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicaciones));
    }

    @GetMapping("/listarNombre")
    public ResponseEntity<MensajeDTO> listarPublicacionNombre(@RequestParam String nombre) throws Exception {
        List<PublicacionGetDTO> publicaciones = publicacionServicio.listarPublicacionNombre(nombre);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicaciones));
    }

    @GetMapping("/listarPrecio")
    public ResponseEntity<MensajeDTO> listarPublicacionPrecio(@RequestParam double precioMinimo, @RequestParam double precioMaximo) throws Exception {
        List<PublicacionGetDTO> publicaciones = publicacionServicio.listarPublicacionPrecio(precioMinimo, precioMaximo);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicaciones));
    }

    @GetMapping("/listarOfertas")
    public ResponseEntity<MensajeDTO> listarOfertas() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicacionServicio.listarOfertas()));
    }

    @GetMapping("/caroBaratoCategoria")
    public ResponseEntity<MensajeDTO> caroBaratoCategoria(@RequestParam Categoria categoria) throws Exception {
        List<PublicacionGetDTO> publicaciones = publicacionServicio.caroBaratoCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicaciones));
    }
}
