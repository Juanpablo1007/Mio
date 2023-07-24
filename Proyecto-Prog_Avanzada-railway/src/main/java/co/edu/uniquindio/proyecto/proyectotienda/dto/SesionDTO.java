package co.edu.uniquindio.proyecto.proyectotienda.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class SesionDTO {

    @NotEmpty(message = "El correo no puede estar vacío.")
    @Email(message = "El correo no tiene el formato correcto.")
    @Length(max = 100, message = "El correo debe tener un máximo de 100 caracteres.")
    private  String email;

    @NotEmpty(message = "La contraseña no puede estar vacía.")
    @Length(max = 50, message = "La contraseña debe tener un máximo de 50 caracteres.")
    private String contrasenia;

}
