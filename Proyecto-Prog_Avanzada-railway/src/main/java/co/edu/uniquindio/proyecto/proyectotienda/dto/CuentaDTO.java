package co.edu.uniquindio.proyecto.proyectotienda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class CuentaDTO {

    @NotNull(message = "{cuenta.email.null}")
    @NotBlank(message = "{cuenta.email.empty}")
    @Length(max = 100, message = "{cuenta.email.maxlength}")
    private String email;

    @NotNull(message = "{cuenta.password.null}")
    @NotBlank(message = "{cuenta.password.empty}")
    @Length(max = 30, message = "{cuenta.password.maxlength}")
    private String contrasenia;
}
