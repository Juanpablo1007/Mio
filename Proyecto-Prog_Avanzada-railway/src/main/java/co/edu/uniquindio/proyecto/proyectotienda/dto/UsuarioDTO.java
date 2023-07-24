package co.edu.uniquindio.proyecto.proyectotienda.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    @NotEmpty(message = "El nombre del usuario no puede estar vacío.")
    @Length(max = 150, message = "El nombre debe tener maximo 100 caracteres")
    private String nombre;
    @NotEmpty(message = "El apellido del usuario no puede estar vacío.")
    @Length(max = 150, message = "El apellido debe tener maximo 100 caracteres")
    private String apellido;
    @NotEmpty(message = "El correo del usuario no puede estar vacío.")
    @Email(message = "El correo no tiene el formato correcto.")
    @Length(max = 100, message = "El correo debe tener maximo 100 caracteres")
    private String email;
    @NotEmpty(message = "La contraseña del usuario no puede estar vacío.")
    @Length(max = 50, message = "La contraseña debe tener maximo 50 caracteres")
    private String contrasenia;
    @NotEmpty(message = "La dirección del usuario no puede estar vacío.")
    @Length(max = 100, message = "La direccion debe tener maximo 100 caracteres")
    private String direccion;
    @NotEmpty(message = "El teléfono del usuario no puede estar vacío.")
    @Length(max = 12, message = "El telefono debe tener maximo 12 caracteres")
    private String telefono;
}
