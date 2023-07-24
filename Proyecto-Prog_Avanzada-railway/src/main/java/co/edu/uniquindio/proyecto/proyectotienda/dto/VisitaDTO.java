package co.edu.uniquindio.proyecto.proyectotienda.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class VisitaDTO {

    @NotNull
    @NotBlank
    private int codigoPublicacion;

    @NotNull
    @NotBlank
    private int codigoCuenta;
}
