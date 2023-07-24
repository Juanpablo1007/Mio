package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Carrito;
import co.edu.uniquindio.proyecto.entidades.CarritoProductos;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import lombok.*;


import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarritoGetDTO {

    private String usuarioCedula;
    private List<CarritoProductosGetDTO> productos = new ArrayList<>();
    private Double total;

}
