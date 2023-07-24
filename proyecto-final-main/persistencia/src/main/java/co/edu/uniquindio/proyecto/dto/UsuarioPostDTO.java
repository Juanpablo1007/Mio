package co.edu.uniquindio.proyecto.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioPostDTO {
    @NotEmpty(message = "La cedula del usuario no puede estar vacia")
    @Length(max = 10, message = "La cedula del usuario debe ser de 10 caracteres o menos")
    private String cedula;
    @NotEmpty(message = "la contraseña del usuario no puede estar vacia")
    @Length(max = 100, message = "La contraseña del usuario debe ser de 100 caracteres o menos")
    private String contraseña;
    @NotEmpty(message = "El nombre del usuario no puede estar vacio")
    @Length(max = 100, message = "El nombre del usuario debe ser de 100 caracteres o menos")
    private String nombre;
    @NotEmpty(message = "El email del usuario no puede estar vacio")
    @Length(max = 100, message = "El email del usuario debe ser de 100 caracteres o menos")
    private String email;
    @NotNull(message = "El usuario debe tener la cuenta activa o desactivada")
    private Boolean isCuentaActiva;
    @NotEmpty(message = "El telefono del usuario no puede estar vacio")
    @Length(max = 100, message = "El telefono del usuario debe ser de 100 caracteres o menos")
    private String telefono;
    @NotEmpty(message = "La direccion del usuario no puede estar vacio")
    @Length(max = 100, message = "La direccion del usuario debe ser de 100 caracteres o menos")
    private String direccion;

}
