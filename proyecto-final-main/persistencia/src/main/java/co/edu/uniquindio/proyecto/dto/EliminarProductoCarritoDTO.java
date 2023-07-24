package co.edu.uniquindio.proyecto.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EliminarProductoCarritoDTO {

    @NotEmpty(message = "La cedula del usuario propietario del carrito no puede estar vacia")
    String usuarioCedulaCarrito;
    @NotNull(message = "El codigo del producto que quiere eliminar del carrito no puede estar vacio")
    Integer productoCodigo;

}
