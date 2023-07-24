package co.edu.uniquindio.proyecto.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GestionFavoritosDTO {

    @NotEmpty(message = "La cedula del usuario logueado no puede estar vacia")
    String usuarioCedula;
    @NotNull(message = "El codigo del producto que quiere agregar/quitar de sus favoritos no puede estar vacio")
    Integer productoCodigo;

}
