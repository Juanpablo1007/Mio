package co.edu.uniquindio.proyecto.proyectotienda.controladores;

import co.edu.uniquindio.proyecto.proyectotienda.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) throws Exception {
        usuarioServicio.crearUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Usuario creado correctamente."));
    }

    @PutMapping("/actualizar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> actualizarUsuario(@PathVariable int codigoUsuario, @RequestBody @Valid UsuarioDTO usuarioDTO) throws Exception {
        usuarioServicio.actualizarUsuario(codigoUsuario, usuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "Usuario actualizado con éxito."));
    }

    @DeleteMapping("/eliminar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> eliminarUsuario(@PathVariable int codigoUsuario) throws Exception {
        usuarioServicio.eliminarUsuario(codigoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "Usuario eliminado correctamente."));
    }

    @GetMapping("/obtener/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> obtenerUsuarioDTO(@PathVariable int codigoUsuario) throws Exception {
        UsuarioGetDTO usuario = usuarioServicio.obtenerUsuarioDTO(codigoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, usuario));
    }

    @PostMapping("/favoritoAgregar")
    public ResponseEntity<MensajeDTO> marcarFavorito(@RequestParam int codigoCuenta, @RequestParam int codigoPublicacion) throws Exception {
        usuarioServicio.marcarFavorito(codigoCuenta, codigoPublicacion);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "Favorito agregado correctamente."));
    }

    @DeleteMapping("/favoritoEliminar")
    public ResponseEntity<MensajeDTO> eliminarFavorito(@RequestParam int codigoCuenta, @RequestParam int codigoPublicacion) throws Exception {
        usuarioServicio.eliminarFavorito(codigoCuenta, codigoPublicacion);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "Favorito eliminado correctamente."));
    }


}
