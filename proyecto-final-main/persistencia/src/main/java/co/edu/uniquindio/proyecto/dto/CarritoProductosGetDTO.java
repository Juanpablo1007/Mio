package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarritoProductosGetDTO {

    private ProductoGetDTO productoGetDTO;

    private Integer unidades;

}
