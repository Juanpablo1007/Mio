package co.edu.uniquindio.proyecto.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RangoPreciosDTO {

    @PositiveOrZero(message = "El precio minimo tiene que ser positivo")
    Double precioMinimo;
    @PositiveOrZero(message = "El precio maximo tiene que ser positivo")
    Double precioMaximo;

}
