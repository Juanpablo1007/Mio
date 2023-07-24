package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.ModeradorPostDTO;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(value = "api/moderadores")
@PreAuthorize("hasAuthority('MODERADOR')")
public class ModeradorController {
    private final ModeradorServicio servicio;
    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> registrar(@Valid @RequestBody ModeradorPostDTO moderadorPostDTO) throws Exception {
        servicio.registrarModerador(moderadorPostDTO);
        return ResponseEntity.status(201).body(new MensajeDTO(HttpStatus.CREATED, false,
                "Moderador creado correctamente"));
    }
    @PutMapping("/autorizar/{codigo}")
    public ResponseEntity<MensajeDTO> autorizarProducto(@PathVariable Integer codigo) throws Exception {

        servicio.autorizarProducto(codigo);
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Producto autorizado con exito" ) );
    }
    @PutMapping("/prohibir/{codigo}")
    public ResponseEntity<MensajeDTO> prohibirProducto(@PathVariable Integer codigo) throws Exception {

        servicio.prohibirProducto(codigo);
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Producto prohibido con exito" ) );
    }
    @GetMapping("/productos_estado")
    public ResponseEntity<MensajeDTO> listarProductosPorEstado() throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                servicio.listarProductosPorEstado() ) );
    }

    @GetMapping("/obtener/{cedula}")
    public ResponseEntity<MensajeDTO> obtenerModerador(@PathVariable String cedula) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                servicio.obtenerModeradorPorCedula(cedula)));
    }
    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO> listarModeradores() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                servicio.listarModeradores()));
    }

}
