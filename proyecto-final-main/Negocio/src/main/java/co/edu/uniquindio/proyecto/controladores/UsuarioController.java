package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioPostDTO;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CarritoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(value = "api/usuarios")
public class UsuarioController {
    private final UsuarioServicio servicio;


    @PutMapping("/actualizar")
    public ResponseEntity<MensajeDTO> actualizarUsuario(@Valid @RequestBody UsuarioPostDTO usuarioPostDTO) throws Exception {

        servicio.actualizarUsuario(usuarioPostDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                "Usuario actualizado correctamente"));
    }


    @DeleteMapping("/eliminar/{cedula}")
    public ResponseEntity<MensajeDTO> eliminar(@PathVariable String cedula) throws Exception {
        servicio.eliminarUsuario(cedula);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                "Usuario eliminado correctamente"));
    }

    @GetMapping("/obtener/{cedula}")
    public ResponseEntity<MensajeDTO> obtener(@PathVariable String cedula) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                servicio.buscarUsuario(cedula)));
    }
    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO> listarUsuarios() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                servicio.listarUsuarios()));
    }
    @GetMapping("/obtener/{cedula}")
    public ResponseEntity<MensajeDTO> obtenerUsuariosEmail(@PathVariable String correo) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                servicio.ObtenerUsuario(correo));
    }



}
