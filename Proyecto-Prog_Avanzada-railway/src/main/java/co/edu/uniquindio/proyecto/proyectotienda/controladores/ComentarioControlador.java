package co.edu.uniquindio.proyecto.proyectotienda.controladores;

import co.edu.uniquindio.proyecto.proyectotienda.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.ComentarioGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api/comentario")
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearComentario(@RequestBody @Valid ComentarioDTO comentarioDTO) throws Exception {
        comentarioServicio.crearComentario(comentarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Comentario publicado correctamente."));
    }

    @GetMapping("/listar/{codigoPublicacion}")
    public ResponseEntity<MensajeDTO> listarComentariosPublicacion(@PathVariable int codigoPublicacion) throws Exception {
        List<ComentarioGetDTO> comentarios = comentarioServicio.listarComentariosPublicacion(codigoPublicacion);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, comentarios));
    }
}
