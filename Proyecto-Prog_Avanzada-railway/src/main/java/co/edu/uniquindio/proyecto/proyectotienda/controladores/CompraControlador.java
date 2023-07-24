package co.edu.uniquindio.proyecto.proyectotienda.controladores;

import co.edu.uniquindio.proyecto.proyectotienda.dto.CompraDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.CompraGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.PublicacionGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.CompraServicio;
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
@RequestMapping("/api/compra")
public class CompraControlador {

    private final CompraServicio compraServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearCompra(@RequestBody @Valid CompraDTO compraDTO) throws Exception {
        compraServicio.crearCompra(compraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Compra creada correctamente."));
    }

    @GetMapping("/listar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> listarCompras(@PathVariable int codigoUsuario) throws Exception {
        List<CompraGetDTO> compras = compraServicio.listarCompras(codigoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, compras));
    }

    @GetMapping("/obtener/{codigoCompra}")
    public ResponseEntity<MensajeDTO> obtenerCompra(@PathVariable int codigoCompra) throws Exception {
        CompraGetDTO compra = compraServicio.obtenerCompraDTO(codigoCompra);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, compra));
    }

    @GetMapping("/productosComprados")
    public ResponseEntity<MensajeDTO> compraUsuarioNoRepetir(@RequestParam int codigoUsuario) throws Exception {
        List<PublicacionGetDTO> publicaciones = compraServicio.obtenerComprasUsuarioNoRepetir(codigoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, publicaciones));
    }
}
