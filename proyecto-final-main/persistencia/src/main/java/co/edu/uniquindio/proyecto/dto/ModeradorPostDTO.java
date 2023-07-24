package co.edu.uniquindio.proyecto.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModeradorPostDTO {
    @NotEmpty(message = "La cedula del moderador no puede estar vacia")
    @Length(max = 10, message = "La cedula del moderador debe ser de 10 caracteres o menos")
    private String cedula;
    @NotEmpty(message = "El nombre del moderador no puede estar vacio")
    @Length(max = 100, message = "El nombre del moderador debe ser de 100 caracteres o menos")
    private String nombre;
    @NotEmpty(message = "El email del moderador no puede estar vacio")
    @Length(max = 100, message = "El email del moderador debe ser de 100 caracteres o menos")
    private String email;
    @NotEmpty(message = "la contraseña del moderador no puede estar vacia")
    @Length(max = 100, message = "La contraseña del moderador debe ser de 100 caracteres o menos")
    private String contraseña;

}
