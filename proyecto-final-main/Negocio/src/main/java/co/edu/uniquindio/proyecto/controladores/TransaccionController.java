package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.TransaccionPostDTO;
import co.edu.uniquindio.proyecto.servicios.TransaccionServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping( value = "api/transacciones")
public class TransaccionController {

   private final TransaccionServicio servicio;
    @PostMapping("realizar_transaccion")
    public ResponseEntity<MensajeDTO> realizarTransaccion(@Valid @RequestBody TransaccionPostDTO transaccionPostDTO) throws Exception {

        servicio.realizarTransaccion(transaccionPostDTO);
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.CREATED, false,
                "La transaccion ha sido exitosa" ) );
    }

}
