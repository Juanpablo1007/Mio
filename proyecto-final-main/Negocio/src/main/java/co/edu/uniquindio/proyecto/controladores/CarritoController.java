package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.CarritoProductosPostDTO;
import co.edu.uniquindio.proyecto.dto.EliminarProductoCarritoDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;

import co.edu.uniquindio.proyecto.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CarritoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping( value = "api/carritos")
public class CarritoController {

    private final CarritoServicio servicio;
    @DeleteMapping("/eliminar_producto")
    public ResponseEntity<MensajeDTO> eliminarProducto(@Valid @RequestBody EliminarProductoCarritoDTO eliminarProductoCarritoDTO) throws Exception {

        servicio.eliminarProducto(eliminarProductoCarritoDTO);
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Se elimino el producto de manera satisfactoria" ) );
    }
    @PostMapping("/agregar_producto")
    public ResponseEntity<MensajeDTO> agregarProducto(@Valid @RequestBody CarritoProductosPostDTO carritoProductosPostDTO) throws Exception {

        servicio.agregarProducto(carritoProductosPostDTO);
        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Se agrego el producto de manera satisfactoria" ) );
    }

}
