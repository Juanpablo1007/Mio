package co.edu.uniquindio.proyecto.proyectotienda.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Getter
@Setter
public class ComentarioDTO {

     @NotEmpty(message = "El comentario no puede estar vacío")
     @Length(max = 255, message = "El comentario debe tener un máximo de 255 caracteres")
     private String mensaje;
     private int codigoUsuario;
     private int codigoPublicacion;
}
