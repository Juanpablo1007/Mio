package co.edu.uniquindio.proyecto.proyectotienda.controladores;

import co.edu.uniquindio.proyecto.proyectotienda.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.PublicacionDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.PublicacionGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.ModeradorServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/moderador")
public class ModeradorControlador {

    private ModeradorServicio moderadorServicio;

    @PutMapping("/revisarPublicacion")
    public ResponseEntity<MensajeDTO> revisar(@RequestBody RevisionDTO revisionDTO) throws Exception {
        PublicacionGetDTO publicacion = moderadorServicio.revisarPublicacion(revisionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicacion));
    }
}
