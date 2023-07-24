package co.edu.uniquindio.proyecto.proyectotienda.controladores;

import co.edu.uniquindio.proyecto.proyectotienda.dto.CuentaDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.CuentaGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.CuentaServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/cuenta")
public class CuentaControlador {

    private CuentaServicio cuentaServicio;

    @GetMapping("/recuperarContra")
    public ResponseEntity<MensajeDTO> recuperarContrasenia(@RequestParam String email) throws Exception {
        cuentaServicio.recuperarContrasenia(email);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "Solicitud de cambio de contraseña generada."));
    }


    @PutMapping("/cambiarContra")
    public ResponseEntity<MensajeDTO> cambiarContrasenia(@RequestBody @Valid CuentaDTO cuentaDTO) throws Exception {
        cuentaServicio.cambiarContrasenia(cuentaDTO.getEmail(), cuentaDTO.getContrasenia());
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, "La contraseña ha sido cambiada."));
    }

    @GetMapping("/obtenerCuentaEmail")
    public ResponseEntity<MensajeDTO> obtenerCuentaEmail(@RequestParam String email) throws Exception {
        CuentaGetDTO cuenta = cuentaServicio.buscarCuentaEmailDTO(email);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, cuenta));
    }
}
