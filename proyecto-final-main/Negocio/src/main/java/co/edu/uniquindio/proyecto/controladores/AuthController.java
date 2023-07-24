package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioPostDTO;
import co.edu.uniquindio.proyecto.servicios.CarritoServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.SesionServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private final UsuarioServicio usuarioServicio;
    private final ProductoServicio productoServicio;
    private final SesionServicio sesionServicio;

    private final CarritoServicio carritoServicio;
    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO loginUser) {
        TokenDTO jwtTokenDto = sesionServicio.loginUsuario(loginUser);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                jwtTokenDto) );
    }

    @GetMapping("/recuperar_contraseña/{email}")
    public ResponseEntity<MensajeDTO> recuperarContraseña(@PathVariable String email) throws Exception {
        sesionServicio.recuperarContraseña(email);
        return ResponseEntity.status(200).body(new MensajeDTO(HttpStatus.OK, false,
                "Se ha enviado una contraseña nueva a su correo electronico"));
    }

    @PostMapping("/crear_usuario")
    public ResponseEntity<MensajeDTO> registrarUsuario(@Valid @RequestBody UsuarioPostDTO usuarioPostDTO) throws Exception {
        usuarioServicio.registrarUsuario(usuarioPostDTO);
        carritoServicio.asignarCarrito(usuarioPostDTO.getCedula());
        return ResponseEntity.status(HttpStatus.CREATED).body( new
                MensajeDTO(HttpStatus.CREATED, false, "Usuario creado correctamente") );
    }
    @GetMapping("/obtener_producto/{codigo}")
    public ResponseEntity<MensajeDTO> obtenerProducto(@PathVariable Integer codigo) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                productoServicio.obtenerProducto(codigo)));
    }
    @GetMapping("/obtener_productos")
    public ResponseEntity<MensajeDTO> obtenerProductos() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                productoServicio.listarProductos()));
    }


}