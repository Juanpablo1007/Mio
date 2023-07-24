package co.edu.uniquindio.proyecto.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarritoProductosPostDTO {


    @NotEmpty(message = "La cedula del usuario propietario del carrito no puede estar vacia")
    String usuarioCedulaCarrito;
    @NotNull(message = "El codigo del producto que quiere agregar al carrito no puede estar vacio")
    Integer productoCodigo;

    @Min(value = 1, message = "Se debe tener al menos una unidad del producto en el carrito")
    Integer unidades;

}
