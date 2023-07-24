package co.edu.uniquindio.proyecto.proyectotienda.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class ModeradorDTO {

    @NotNull
    @NotBlank
    @Length(max = 150, message = "El nombre debe tener maximo 100 caracteres")
    private String nombre;
    @NotNull
    @NotBlank
    @Length(max = 150, message = "El nombre debe tener maximo 100 caracteres")
    private String apellido;
    @NotNull
    @NotBlank
    @Length(max = 100, message = "El correo debe tener maximo 100 caracteres")
    private String email;
    @NotNull
    @NotBlank
    @Length(max = 50, message = "La contraseña debe tener maximo 50 caracteres")
    private String contrasenia;
    @NotNull
    @NotBlank
    @Length(max = 12, message = "El telefono debe tener maximo 12 caracteres")
    private String telefono;

}
